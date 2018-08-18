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
 * 提交详细的用户基本信息
 * Created by zsw on 2017/9/2.
 *
 */
@Service
public class UserBaseInfoTask implements Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserBaseInfoTask.class);

    @Resource
    private DispatchRunDomain dispatchRunDomain;
    @Resource
    private UserDataDomain userDataDomain;


    @Override
    public DispatchRunResponseBo run(Integer taskInstanceId) {
        LOGGER.info("【提交详细的用户基本信息 taskInstanceId={}】", taskInstanceId);

        TaskInstanceBo taskInstatnceBo = null;
        try {
            taskInstatnceBo = dispatchRunDomain.getTaskInstanceBo(taskInstanceId);
            Integer id = userDataDomain.fillUserBaseInfo(taskInstatnceBo.getUserDataId(), taskInstatnceBo.getParamsJson());
            if (id != null) {
                JSONObject finishParams = StringUtils.isEmpty(taskInstatnceBo.getParamsJson())?new JSONObject():JSONObject.parseObject(taskInstatnceBo.getParamsJson());
                finishParams.put(TaskParamConstant.USER_DATA_METADATA_ID, id);
                dispatchRunDomain.finishTaskInstance(taskInstanceId, finishParams.toJSONString(), TaskStatus.SUCCESS);

                return DispatchRunResponseBo.success();
            } else {
                dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
                return DispatchRunResponseBo.error(StatusCode.FULL_USER_BASE_INFO_FAIL);
            }
        } catch (Exception e) {
            LOGGER.error("【提交详细的用户基本信息异常，异常信息： 】", e);
            dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo == null?null:taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
            return DispatchRunResponseBo.error(StatusCode.FULL_USER_BASE_INFO_FAIL, e.getMessage());
        }
    }
}
