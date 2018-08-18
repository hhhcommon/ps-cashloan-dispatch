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
 * Created by zsw on 2018/8/3 0003.
 */
@Service
public class EmergencyContactTask implements Task {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserBaseInfoTask.class);

    @Resource
    private DispatchRunDomain dispatchRunDomain;
    @Resource
    private UserDataDomain userDataDomain;

    @Override
    public DispatchRunResponseBo run(Integer taskInstanceId) {
        LOGGER.info("【提交app紧急联系人信息 taskInstanceId={}】", taskInstanceId);

        TaskInstanceBo taskInstatnceBo = null;
        try {
            taskInstatnceBo = dispatchRunDomain.getTaskInstanceBo(taskInstanceId);
            JSONObject parseObject = JSONObject.parseObject(taskInstatnceBo.getParamsJson());
            if (!parseObject.containsKey(TaskParamConstant.THIRD_PARTY_METADATA)) {
                dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
                return DispatchRunResponseBo.error(StatusCode.OTHER_ERROR, "需要的参数为空");
            }
            Integer id = userDataDomain.fillEmergency(taskInstatnceBo.getUserDataId(), parseObject.getString(TaskParamConstant.THIRD_PARTY_METADATA));
            if (id != null) {
                JSONObject finishParams = StringUtils.isEmpty(taskInstatnceBo.getParamsJson())?new JSONObject():JSONObject.parseObject(taskInstatnceBo.getParamsJson());
                finishParams.put(TaskParamConstant.USER_DATA_METADATA_ID, id);
                dispatchRunDomain.finishTaskInstance(taskInstanceId, finishParams.toJSONString(), TaskStatus.SUCCESS);

                return DispatchRunResponseBo.success();
            } else {
                dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
                return DispatchRunResponseBo.error(StatusCode.FULL_EMERGENCY_INFO_FAIL);
            }
        } catch (Exception e) {
            LOGGER.error("【提交紧急联系人信息异常，异常信息： 】", e);
            dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo == null?null:taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
            return DispatchRunResponseBo.error(StatusCode.FULL_EMERGENCY_INFO_FAIL, e.getMessage());
        }
    }
}
