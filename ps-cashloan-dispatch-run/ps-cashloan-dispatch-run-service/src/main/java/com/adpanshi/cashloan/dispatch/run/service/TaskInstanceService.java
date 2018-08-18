package com.adpanshi.cashloan.dispatch.run.service;

import com.adpanshi.cashloan.dispatch.run.enums.TaskStatus;
import com.adpanshi.cashloan.dispatch.run.mapper.TaskInstanceMapper;
import com.adpanshi.cashloan.dispatch.run.model.TaskInstanceExample;
import com.adpanshi.cashloan.dispatch.run.model.TaskInstanceWithBLOBs;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 任务实例
 * Created by zsw on 2018/6/22 0022.
 */
@Service
public class TaskInstanceService {

    @Resource
    private TaskInstanceMapper taskInstanceMapper;

    /**
     * 添加
     */
    public TaskInstanceWithBLOBs create(String adapterCode, Integer nodeInstId, Integer nodeId, Integer taskId, Integer userDataId, String mobile, String email,  String userIdCard, String userName, String deviceFingerprint, String paramsJson, Boolean isSync) {
        TaskInstanceWithBLOBs instance = new TaskInstanceWithBLOBs();
        instance.setAdapterCode(adapterCode);
        instance.setNodeInstId(nodeInstId);
        instance.setNodeId(nodeId);
        instance.setTaskId(taskId);
        instance.setUserDataId(userDataId);
        instance.setUserMobile(mobile);
        instance.setUserEmail(email);
        instance.setUserIdCard(userIdCard);
        instance.setUserName(userName);
        instance.setDeviceFingerprint(deviceFingerprint);
        instance.setParamsJson(paramsJson);
        instance.setIsSync(isSync);
        instance.setIsdelete(false);
        instance.setStatus(TaskStatus.INIT.getValue());
        instance.setCreateTime(new Date());
        taskInstanceMapper.insert(instance);
        return instance;
    }

    /**
     * 获取节点实例ID
     */
    public Integer getNodeInstId(Integer id){
        return taskInstanceMapper.selectNodeInstId(id);
    }

    public Integer getUserDataId(Integer id){
        return taskInstanceMapper.selectUserDataId(id);
    }

    /**
     * 获取
     */
    public TaskInstanceWithBLOBs get(Integer id) {
        TaskInstanceWithBLOBs instance = taskInstanceMapper.selectByPrimaryKey(id);
        if (instance == null || instance.getIsdelete()) {
            return null;
        }
        return instance;
    }

    /**
     * 添加任务参数
     */
    public void addParam(Integer taskInstId, Map<String,Object> map) {
        TaskInstanceWithBLOBs taskInst = get(taskInstId);
        JSONObject params = StringUtils.isEmpty(taskInst.getParamsJson()) ? new JSONObject() : JSONObject.parseObject(taskInst.getParamsJson());
        params.putAll(map);
        TaskInstanceWithBLOBs modifyTaskInst = new TaskInstanceWithBLOBs();
        modifyTaskInst.setId(taskInstId);
        modifyTaskInst.setParamsJson(params.toJSONString());
        taskInstanceMapper.updateByPrimaryKeySelective(modifyTaskInst);
    }

    /**
     * 完成
     */
    public void finish(Integer taskInstatnceId, String params, TaskStatus status) {
        TaskInstanceWithBLOBs instance = get(taskInstatnceId);
        instance.setFinishParamsJson(params);
        instance.setStatus(status.getValue());
        instance.setLastModifyTime(new Date());
        taskInstanceMapper.updateByPrimaryKeyWithBLOBs(instance);
    }

    /**
     * 根据节点Id和任务Id查找
     */
    public List<TaskInstanceWithBLOBs> findPassByNodeInstIdAndTaskId(Integer nodeInstanceId, Integer taskId){
        List<Integer> passStatus = new ArrayList<>();
        passStatus.add(TaskStatus.SUCCESS.getValue());
        passStatus.add(TaskStatus.SKIP.getValue());
        TaskInstanceExample example = new TaskInstanceExample();
        example.createCriteria().andIsdeleteEqualTo(false).andNodeInstIdEqualTo(nodeInstanceId).andTaskIdEqualTo(taskId).andStatusIn(passStatus);
        return taskInstanceMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 查找父级任务
     */
    public List<TaskInstanceWithBLOBs> findParentTaskInstByTaskInstId(Integer taskInstId){
        return taskInstanceMapper.findParentTaskInsByTaskInsId(taskInstId);
    }

    public TaskInstanceWithBLOBs findDecisionTreeTaskById(String adapterCode, Integer userDataId){
        TaskInstanceExample example = new TaskInstanceExample();
        example.createCriteria().andIsdeleteEqualTo(false).andUserDataIdEqualTo(userDataId).andAdapterCodeEqualTo(adapterCode);
        List<TaskInstanceWithBLOBs> taskInstanceWithBLOBs = taskInstanceMapper.selectByExampleWithBLOBs(example);
        if (taskInstanceWithBLOBs == null || taskInstanceWithBLOBs.isEmpty()){
            return null;
        }
        Collections.reverse(taskInstanceWithBLOBs);
        return taskInstanceWithBLOBs.get(0);
    }
}
