package com.adpanshi.cashloan.dispatch.config.domain;


import com.adpanshi.cashloan.dispatch.config.bo.NodeConfigBo;
import com.adpanshi.cashloan.dispatch.config.bo.TaskConfigBo;

import java.util.List;

/**
 * 调度中心配置
 * Created by zsw on 2018/6/29 0029.
 */
public interface DispatchConfigDomain {

    /**
     * 获取一个节点配置
     * @param number 节点编码
     */
    NodeConfigBo getNodeByNumber(String number);

    /**
     * 获取一个任务配置
     */
    TaskConfigBo getTask(Integer id);

    /**
     * 获取一个节点的同步任务(ps:一个节点最多只有一个同步任务)
     * @param nodeNumber 节点编码
     */
    TaskConfigBo getSyncTask(String nodeNumber);

    /**
     * 查找一个节点的起始异步任务列表(没有上级任务)
     */
    List<TaskConfigBo> findNoParentAsyncTask(String nodeNumber);

    /**
     * 查找一个任务的子任务
     */
    List<TaskConfigBo> findTaskByParentId(Integer parentId);

    /**
     * 查找一个任务的父任务
     */
    List<TaskConfigBo> findTaskBySonId(Integer sonId);
}
