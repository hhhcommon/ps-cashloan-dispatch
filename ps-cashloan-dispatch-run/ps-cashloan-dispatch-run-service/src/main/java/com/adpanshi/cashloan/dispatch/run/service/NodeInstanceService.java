package com.adpanshi.cashloan.dispatch.run.service;

import com.adpanshi.cashloan.dispatch.run.enums.NodeStatus;
import com.adpanshi.cashloan.dispatch.run.enums.TaskStatus;
import com.adpanshi.cashloan.dispatch.run.mapper.NodeInstanceMapper;
import com.adpanshi.cashloan.dispatch.run.model.NodeInstance;
import com.adpanshi.cashloan.dispatch.run.model.TaskInstanceWithBLOBs;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * Created by zsw on 2018/6/22 0022.
 */
@Service
public class NodeInstanceService {

    @Resource
    private NodeInstanceMapper nodeInstanceMapper;

    public NodeInstance create(Integer nodeId, Integer userDataId, String mobile, String email, String userIdCard, String userName, String deviceFingerprint, Map<String,String> paramsJson){
        NodeInstance instance = new NodeInstance();
        instance.setNodeId(nodeId);
        instance.setUserDataId(userDataId);
        instance.setUserMobile(mobile);
        instance.setUserEmail(email);
        instance.setUserIdCard(userIdCard);
        instance.setUserName(userName);
        instance.setDeviceFingerprint(deviceFingerprint);
        instance.setParamsJson(JSONObject.toJSONString(paramsJson));
        instance.setStatus(NodeStatus.INIT.getValue());
        instance.setCreateTime(new Date());
        instance.setIsdelete(false);
        nodeInstanceMapper.insert(instance);
        return instance;
    }

    /**
     * 获取节点实例
     */
    public NodeInstance get(Integer id) {
        NodeInstance instance = nodeInstanceMapper.selectByPrimaryKey(id);
        if (instance == null || instance.getIsdelete()) {
            return null;
        }
        return instance;
    }

    /**
     * 完成
     */
    public void finish(Integer nodeId, NodeStatus status) {
        NodeInstance instance = this.get(nodeId);
        instance.setStatus(status.getValue());
        instance.setLastModifyTime(new Date());
        nodeInstanceMapper.updateByPrimaryKeyWithBLOBs(instance);
    }
}
