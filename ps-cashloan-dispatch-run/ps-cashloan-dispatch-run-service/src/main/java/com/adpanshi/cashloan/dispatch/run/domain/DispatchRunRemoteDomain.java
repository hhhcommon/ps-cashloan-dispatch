package com.adpanshi.cashloan.dispatch.run.domain;

import com.adpanshi.cashloan.dispatch.run.bo.DispatchRunResponseBo;
import com.adpanshi.cashloan.dispatch.run.bo.TaskInstanceBo;
import com.adpanshi.cashloan.dispatch.run.enums.TaskStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    public DispatchRunResponseBo runByTaskId(Integer taskInstId ) {
        return dispatchRunNativeDomain.runByTaskId(taskInstId);
    }

    @Override
    public List<TaskInstanceBo> findParentTaskInstByTaskInstId(Integer taskInstanceId) {
        return dispatchRunNativeDomain.findParentTaskInstByTaskInstId(taskInstanceId);
    }

    @Override
    public TaskInstanceBo findDecisionTreeTaskById(Integer userDataId) {
        return dispatchRunNativeDomain.findDecisionTreeTaskById(userDataId);
    }

    @Override
    public void finishTaskInstance(Integer taskInstatnceId, String params, TaskStatus status) {
        dispatchRunNativeDomain.finishTaskInstance(taskInstatnceId, params, status);
    }

    @Override
    public DispatchRunResponseBo startNode(Integer userDataId, String nodeNumber, String mobile, String email, String aadhaarNo, String userName, String deviceFingerprint, Map<String, String> params) {
        return dispatchRunNativeDomain.startNode(userDataId, nodeNumber, mobile, email, aadhaarNo, userName, deviceFingerprint, params);
    }

    @Override
    public TaskInstanceBo getTaskInstanceBo(Integer taskInstatnceId) {
        return dispatchRunNativeDomain.getTaskInstanceBo(taskInstatnceId);
    }

    @Override
    public void addParam(Integer taskInstId, Map<String, Object> map) {

    }
}
