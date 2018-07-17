package com.adpanshi.cashloan.dispatch.config.domain;

import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.dispatch.config.bo.NodeConfigBo;
import com.adpanshi.cashloan.dispatch.config.bo.TaskConfigBo;
import com.adpanshi.cashloan.dispatch.config.model.TaskConfig;
import com.adpanshi.cashloan.dispatch.config.model.TaskRelationConfig;
import com.adpanshi.cashloan.dispatch.config.service.NodeConfigService;
import com.adpanshi.cashloan.dispatch.config.service.TaskConfigService;
import com.adpanshi.cashloan.dispatch.config.service.TaskRelationConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 调度中心配置Domain
 * Created by zsw on 2018/6/29 0029.
 */
@Service
public class DispatchConfigNaitveDomain implements DispatchConfigDomain {

    @Resource
    private NodeConfigService nodeConfigService;
    @Resource
    private TaskConfigService taskConfigService;
    @Resource
    private TaskRelationConfigService taskRelationConfigService;

    @Override
    public NodeConfigBo getNodeByNumber(String number) {
        return BeanUtil.convert(nodeConfigService.getNodeByNumber(number), NodeConfigBo.class);
    }

    @Override
    public TaskConfigBo getTask(Integer id) {
        return BeanUtil.convert(taskConfigService.get(id), TaskConfigBo.class);
    }

    @Override
    public TaskConfigBo getSyncTask(String nodeNumber) {
        NodeConfigBo nodeConfigBo = getNodeByNumber(nodeNumber);
        if (nodeConfigBo == null) {
            return null;
        }
        return BeanUtil.convert(taskConfigService.getSyncTask(nodeConfigBo.getId()), TaskConfigBo.class);
    }

    @Override
    public List<TaskConfigBo> findNoParentAsyncTask(String nodeNumber) {
        return BeanUtil.convertList(taskConfigService.findNoParentAsyncTask(nodeNumber), TaskConfigBo.class);
    }

    @Override
    public List<TaskConfigBo> findTaskByParentId(Integer parentId) {
        List<TaskRelationConfig> relationConfigList = taskRelationConfigService.findRelationByParentId(parentId);
        List<TaskConfigBo> taskConfigBoList = new ArrayList<>();
        for(TaskRelationConfig taskRelationConfig :relationConfigList){
            TaskConfig taskConfig = taskConfigService.get(taskRelationConfig.getSonTaskId());
            taskConfigBoList.add(BeanUtil.convert(taskConfig, TaskConfigBo.class));
        }
        return taskConfigBoList;
    }

    @Override
    public List<TaskConfigBo> findTaskBySonId(Integer sonId) {
        List<TaskRelationConfig> relationConfigList = taskRelationConfigService.findRelationBySonId(sonId);
        List<TaskConfigBo> taskConfigBoList = new ArrayList<>();
        for(TaskRelationConfig taskRelationConfig :relationConfigList){
            TaskConfig taskConfig = taskConfigService.get(taskRelationConfig.getParentTaskId());
            taskConfigBoList.add(BeanUtil.convert(taskConfig, TaskConfigBo.class));
        }
        return taskConfigBoList;
    }
}
