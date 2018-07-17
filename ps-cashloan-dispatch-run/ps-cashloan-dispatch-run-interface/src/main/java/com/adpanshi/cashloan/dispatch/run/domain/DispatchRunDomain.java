package com.adpanshi.cashloan.dispatch.run.domain;

import com.adpanshi.cashloan.dispatch.run.bo.DispatchRunResponseBo;
import com.adpanshi.cashloan.dispatch.run.bo.TaskInstanceBo;
import com.adpanshi.cashloan.dispatch.run.enums.TaskStatus;

/**
 * Created by zsw on 2018/6/22 0022.
 */
public interface DispatchRunDomain {

    /**
     * 运行任务
     */
    DispatchRunResponseBo runTask(Integer taskInstanceId);

    /**
     * 标记完成任务
     */
    void finishTaskInstance(Integer taskInstatnceId, String params, TaskStatus status);

    /**
     * 获取任务实例
     */
    TaskInstanceBo getTaskInstanceBo(Integer taskInstatnceId);

}
