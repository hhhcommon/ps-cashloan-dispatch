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
 * Created by zsw on 2018/8/3 0003.
 */
@Service
public class TongdunCreditBodyguardTask implements Task {

    private final static Logger logger = LoggerFactory.getLogger(TongdunCreditBodyguardTask.class);

    @Resource
    private DispatchRunDomain dispatchRunDomain;

    @Resource
    private UserDataDomain userDataDomain;

    @Override
    public DispatchRunResponseBo run(Integer taskInstanceId) {
        logger.info("开始执行同盾信贷保镖任务,taskInstanceId:{}", taskInstanceId);
        TaskInstanceBo taskInstatnceBo = null;
        try {
            taskInstatnceBo = dispatchRunDomain.getTaskInstanceBo(taskInstanceId);
            JSONObject paramsJson = JSONObject.parseObject(taskInstatnceBo.getParamsJson());
            if (paramsJson == null || !paramsJson.containsKey(TaskParamConstant.THIRD_PARTY_REQUEST_PARAMJSON)) {
                dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
                return DispatchRunResponseBo.error(StatusCode.OTHER_ERROR, "需要的参数为空");
            }
            String tdBodyGuardMetaData = userDataDomain.getTDBodyGuardInfoFromThirdParty(paramsJson.getString(TaskParamConstant.THIRD_PARTY_REQUEST_PARAMJSON));
            if (StringUtil.isBlank(tdBodyGuardMetaData)) {
                dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
                return DispatchRunResponseBo.error(StatusCode.FULL_TD_BODYGUARD_INFO_FAIL);
            }
            Integer id = userDataDomain.fillTDBodyGuardInfo(taskInstatnceBo.getUserDataId(), tdBodyGuardMetaData);
            if (id != null) {
                JSONObject finishParams = StringUtils.isEmpty(tdBodyGuardMetaData) ? new JSONObject() : JSONObject.parseObject(tdBodyGuardMetaData);
                finishParams.put(TaskParamConstant.USER_DATA_METADATA_ID, id);
                dispatchRunDomain.finishTaskInstance(taskInstanceId, finishParams.toJSONString(), TaskStatus.SUCCESS);
                return DispatchRunResponseBo.success(tdBodyGuardMetaData);
            } else {
                dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
                return DispatchRunResponseBo.error(StatusCode.FULL_TD_BODYGUARD_INFO_FAIL);
            }
        } catch (Exception e) {
            logger.error("同盾信贷保镖任务异常，异常信息：", e);
            dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo == null ? null : taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
            return DispatchRunResponseBo.error(StatusCode.FULL_TD_BODYGUARD_INFO_FAIL, e.getMessage());
        }
    }
}
