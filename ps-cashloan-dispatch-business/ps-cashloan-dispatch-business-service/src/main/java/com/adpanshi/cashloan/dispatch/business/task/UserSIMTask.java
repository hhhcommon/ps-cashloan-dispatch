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

import javax.annotation.Resource;

/**
 * 磨盒SIM卡
 * Created by zsw on 2018/8/3 0003.
 */
@Service
public class UserSIMTask implements Task {

    private final static Logger logger = LoggerFactory.getLogger(UserSIMTask.class);

    @Resource
    private DispatchRunDomain dispatchRunDomain;

    @Resource
    private UserDataDomain userDataDomain;

    @Override
    public DispatchRunResponseBo run(Integer taskInstanceId) {
        logger.info("开始执行魔蝎SIM任务,taskInstanceId:{}",taskInstanceId);
        TaskInstanceBo taskInstatnceBo = null;
        try {
            taskInstatnceBo = dispatchRunDomain.getTaskInstanceBo(taskInstanceId);
            JSONObject paramsJson = JSONObject.parseObject(taskInstatnceBo.getParamsJson());
            if (paramsJson == null || !paramsJson.containsKey(TaskParamConstant.THIRD_PARTY_METADATA)) {
                dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
                return DispatchRunResponseBo.error(StatusCode.OTHER_ERROR, "需要的参数为空");
            }
            String simData = paramsJson.getString(TaskParamConstant.THIRD_PARTY_METADATA);
            Integer dataId = userDataDomain.fillSimInfo(taskInstatnceBo.getUserDataId(), simData);
            if (dataId != null) {
                JSONObject finishParams = new JSONObject();
                finishParams.put(TaskParamConstant.USER_DATA_METADATA_ID, dataId);
                dispatchRunDomain.finishTaskInstance(taskInstanceId, finishParams.toJSONString(), TaskStatus.SUCCESS);
                return DispatchRunResponseBo.success();
            } else {
                dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
                return DispatchRunResponseBo.error(StatusCode.FULL_MOXIE_SIM_INFO_FAIL);
            }
        } catch (Exception e) {
            logger.error("魔蝎SIM任务异常，异常信息：", e);
            dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo == null?null:taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
            return DispatchRunResponseBo.error(StatusCode.FULL_MOXIE_SIM_INFO_FAIL, e.getMessage());
        }
    }
}
