package com.adpanshi.cashloan.dispatch.run.domain;

import com.adpanshi.cashloan.dispatch.run.bo.DispatchRunResponseBo;
import com.adpanshi.cashloan.dispatch.run.bo.TaskInstanceBo;
import com.adpanshi.cashloan.dispatch.run.enums.TaskStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zsw on 2018/6/22 0022.
 */
@Service
public class DispatchRunRemoteDomain implements DispatchRunDomain {

    @Resource
    private DispatchRunNativeDomain dispatchRunNativeDomain;

    @Override
    public DispatchRunResponseBo runTask(Integer taskInstanceId) {
        return dispatchRunNativeDomain.runTask(taskInstanceId);
    }

    @Override
    public void finishTaskInstance(Integer taskInstatnceId, String params, TaskStatus status) {
        dispatchRunNativeDomain.finishTaskInstance(taskInstatnceId, params, status);
    }

    @Override
    public TaskInstanceBo getTaskInstanceBo(Integer taskInstatnceId) {
        return dispatchRunNativeDomain.getTaskInstanceBo(taskInstatnceId);
    }
}
