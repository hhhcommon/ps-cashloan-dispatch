package com.adpanshi.cashloan.dispatch.run.domain;

import com.adpanshi.cashloan.dispatch.run.bo.DispatchRunResponseBo;
import com.adpanshi.cashloan.dispatch.run.bo.TaskInstanceBo;
import com.adpanshi.cashloan.dispatch.run.enums.TaskStatus;

import java.util.List;
import java.util.Map;

/**
 * Created by zsw on 2018/6/22 0022.
 */
public interface DispatchRunDomain {


    /**
     * 触发节点
     *
     * @param nodeNumber 节点编码
     */
    DispatchRunResponseBo startNode(Integer userDataId, String nodeNumber, String mobile, String email, String aadhaarNo, String userName, String deviceFingerprint, Map<String, String> params);

    /**
     * 获取任务实例
     */
    TaskInstanceBo getTaskInstanceBo(Integer taskInstatnceId);

    /**
     * 添加参数
     */
    void addParam(Integer taskInstId, Map<String, Object> map);

    /**
     * 标记完成任务
     */
    void finishTaskInstance(Integer taskInstatnceId, String params, TaskStatus status);

    /**
     * 运行任务
     */
    DispatchRunResponseBo runTask(Integer taskInstanceId);

    /**
     * 执行jms返回的异步任务
     * @param  taskInstId
     * @return
     */
    DispatchRunResponseBo runByTaskId(Integer taskInstId);

    /**
     * 获取任务的所有父任务
     */
    List<TaskInstanceBo> findParentTaskInstByTaskInstId(Integer taskInstanceId);

    /**
     * 查询用户贷款记录(这里贷款一个用户只会出现一次)
     * @param userDataId
     * @return
     */
    TaskInstanceBo findDecisionTreeTaskById(Integer userDataId);

}
