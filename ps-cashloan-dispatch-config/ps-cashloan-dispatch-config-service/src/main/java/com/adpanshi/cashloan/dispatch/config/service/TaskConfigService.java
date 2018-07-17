package com.adpanshi.cashloan.dispatch.config.service;

import com.adpanshi.cashloan.dispatch.config.exception.CreditDispatchException;
import com.adpanshi.cashloan.dispatch.config.mapper.TaskConfigMapper;
import com.adpanshi.cashloan.dispatch.config.model.TaskConfig;
import com.adpanshi.cashloan.dispatch.config.model.TaskConfigExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 任务配置Service
 * Created by 王帅 on 2017/8/28.
 */
@Service
public class TaskConfigService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskConfigService.class);

    @Resource
    private TaskConfigMapper taskConfigMapper;

    public TaskConfig getSyncTask(Integer nodeId) {
        TaskConfigExample example = new TaskConfigExample();
        example.createCriteria().andIsdeleteEqualTo(false).andNodeIdEqualTo(nodeId).andIsSyncEqualTo(true);
        List<TaskConfig> taskConfigs = taskConfigMapper.selectByExample(example);
        if (taskConfigs.size() > 1) {
            LOGGER.error("系统配置异常,一个节点只能配置最多一个同步任务,节点id:{}", nodeId);
            throw new CreditDispatchException("系统配置异常,一个节点只能配置最多一个同步任务");
        } else if (taskConfigs.size() == 1) {
            return taskConfigs.get(0);
        } else {
            return null;
        }
    }

    public TaskConfig get(Integer id) {
        TaskConfig taskConfig = taskConfigMapper.selectByPrimaryKey(id);
        if (taskConfig == null || taskConfig.getIsdelete()) {
            return null;
        } else {
            return taskConfig;
        }
    }

    /**
     * 获取无父级异步任务集合
     *
     * @param nodeNumber 节点编码
     */
    public List<TaskConfig> findNoParentAsyncTask(String nodeNumber) {
        return taskConfigMapper.findNoParentAsyncTask(nodeNumber);
    }
}













