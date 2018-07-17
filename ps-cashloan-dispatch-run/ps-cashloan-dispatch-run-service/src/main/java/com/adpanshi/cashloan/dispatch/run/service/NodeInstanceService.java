package com.adpanshi.cashloan.dispatch.run.service;

import com.adpanshi.cashloan.dispatch.run.enums.NodeStatus;
import com.adpanshi.cashloan.dispatch.run.mapper.NodeInstanceMapper;
import com.adpanshi.cashloan.dispatch.run.model.NodeInstance;
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

    public NodeInstance create(Integer nodeId, Integer userDataId, String userAccount, String userIdCard, String userName, String deviceFingerprint, Map<String,String> paramsJson){
        NodeInstance instance = new NodeInstance();
        instance.setNodeId(nodeId);
        instance.setUserDataId(userDataId);
        instance.setUserAccount(userAccount);
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

}
