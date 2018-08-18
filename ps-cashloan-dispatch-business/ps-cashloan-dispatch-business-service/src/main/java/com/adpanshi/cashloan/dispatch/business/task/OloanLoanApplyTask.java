package com.adpanshi.cashloan.dispatch.business.task;

import com.adpanshi.cashloan.data.user.domain.UserDataDomain;
import com.adpanshi.cashloan.dispatch.business.constant.TaskParamConstant;
import com.adpanshi.cashloan.dispatch.run.bo.DispatchRunResponseBo;
import com.adpanshi.cashloan.dispatch.run.bo.TaskInstanceBo;
import com.adpanshi.cashloan.dispatch.run.domain.DispatchRunDomain;
import com.adpanshi.cashloan.dispatch.run.domain.Task;
import com.adpanshi.cashloan.dispatch.run.enums.StatusCode;
import com.adpanshi.cashloan.dispatch.run.enums.TaskStatus;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tool.util.StringUtil;

import javax.annotation.Resource;

/**
 * 贷款申请任务
 * Created by zsw on 2018/8/4 0004.
 */
@Service
public class OloanLoanApplyTask implements Task {
    private static final Logger LOGGER = LoggerFactory.getLogger(OloanLoanApplyTask.class);

    @Resource
    private DispatchRunDomain dispatchRunDomain;
    @Resource
    private UserDataDomain userDataDomain;


    @Override
    public DispatchRunResponseBo run(Integer taskInstanceId) {
        LOGGER.info("【提交贷款申请信息 taskInstanceId={}】", taskInstanceId);

        TaskInstanceBo taskInstatnceBo = null;
        try {
            taskInstatnceBo = dispatchRunDomain.getTaskInstanceBo(taskInstanceId);
            String decisionReulst = userDataDomain.oloanLoanApply(taskInstatnceBo.getUserDataId());
            if (StringUtil.isBlank(decisionReulst)) {
                dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
                return DispatchRunResponseBo.error(StatusCode.OLOAN_LOAN_APPLY_FAIL);
            }
            Integer dataId = userDataDomain.fillLoanApplyInfo(taskInstatnceBo.getUserDataId(), decisionReulst);
            if (dataId != null) {
                JSONObject finishParams = StringUtils.isEmpty(taskInstatnceBo.getParamsJson())?new JSONObject():JSONObject.parseObject(taskInstatnceBo.getParamsJson());
                finishParams.put(TaskParamConstant.USER_DATA_METADATA_ID, dataId);
                dispatchRunDomain.finishTaskInstance(taskInstanceId, finishParams.toJSONString(), TaskStatus.SUCCESS);
                return DispatchRunResponseBo.success(decisionReulst);
            } else {
                dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
                return DispatchRunResponseBo.error(StatusCode.OLOAN_LOAN_APPLY_FAIL);
            }
        } catch (Exception e) {
            LOGGER.error("【提交用户贷款信息异常，异常信息： 】", e);
            dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo == null?null:taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
            return DispatchRunResponseBo.error(StatusCode.OLOAN_LOAN_APPLY_FAIL, e.getMessage());
        }
    }
}
