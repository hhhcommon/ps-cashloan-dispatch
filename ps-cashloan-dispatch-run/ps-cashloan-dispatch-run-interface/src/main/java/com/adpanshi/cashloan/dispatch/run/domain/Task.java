package com.adpanshi.cashloan.dispatch.run.domain;

import com.adpanshi.cashloan.dispatch.run.bo.DispatchRunResponseBo;

/**
 * Created by zsw on 2018/6/22 0022.
 */
public interface Task {

    /**
     * 运行任务
     *
     * @param taskInstanceId 任务实例Id
     * @return 运行结果
     */
    DispatchRunResponseBo run(Integer taskInstanceId);
}
