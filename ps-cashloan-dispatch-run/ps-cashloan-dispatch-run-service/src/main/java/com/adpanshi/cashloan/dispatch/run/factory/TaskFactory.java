package com.adpanshi.cashloan.dispatch.run.factory;

import com.adpanshi.cashloan.dispatch.run.domain.Task;

import java.util.Map;

/**
 * 任务工厂
 * Created by zsw on 2018/6/22 0022.
 */
public class TaskFactory {

    private Map<String, Task> taskMap;

    /**
     * 获取任务实例
     *
     * @param taskNumber 任务编码
     * @return 同步任务实例
     */
    public Task getTask(String taskNumber) {
        return taskMap.get(taskNumber);
    }

    public Map<String, Task> getTaskMap() {
        return taskMap;
    }

    public void setTaskMap(Map<String, Task> taskMap) {
        this.taskMap = taskMap;
    }
}
