package com.adpanshi.cashloan.dispatch.config.service;

import com.adpanshi.cashloan.dispatch.config.mapper.TaskRelationConfigMapper;
import com.adpanshi.cashloan.dispatch.config.model.TaskRelationConfig;
import com.adpanshi.cashloan.dispatch.config.model.TaskRelationConfigExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 任务关系
 * Created by 王帅 on 2017/8/28.
 */
@Service
public class TaskRelationConfigService {

    @Resource
    private TaskRelationConfigMapper taskRelationConfigMapper;

    public List<TaskRelationConfig> findRelationByParentId(Integer parentId) {
        TaskRelationConfigExample example = new TaskRelationConfigExample();
        example.createCriteria().andIsdeleteEqualTo(false).andParentTaskIdEqualTo(parentId);
        return taskRelationConfigMapper.selectByExample(example);
    }

    public List<TaskRelationConfig> findRelationBySonId(Integer sonId) {
        TaskRelationConfigExample example = new TaskRelationConfigExample();
        example.createCriteria().andIsdeleteEqualTo(false).andSonTaskIdEqualTo(sonId);
        return taskRelationConfigMapper.selectByExample(example);
    }
}
