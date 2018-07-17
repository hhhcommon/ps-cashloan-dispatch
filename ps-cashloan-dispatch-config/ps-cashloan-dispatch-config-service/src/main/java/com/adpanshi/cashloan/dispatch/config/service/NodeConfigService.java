package com.adpanshi.cashloan.dispatch.config.service;

import com.adpanshi.cashloan.dispatch.config.mapper.NodeConfigMapper;
import com.adpanshi.cashloan.dispatch.config.model.NodeConfig;
import com.adpanshi.cashloan.dispatch.config.model.NodeConfigExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 王帅 on 2017/8/28.
 */
@Service
public class NodeConfigService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NodeConfigService.class);

    @Resource
    private NodeConfigMapper nodeConfigMapper;

    public NodeConfig getNodeByNumber(String number) {
        NodeConfigExample example = new NodeConfigExample();
        example.createCriteria().andIsdeleteEqualTo(false).andNumberEqualTo(number);
        List<NodeConfig> nodeConfigList = nodeConfigMapper.selectByExample(example);
        if (nodeConfigList.size() > 1) {
            LOGGER.error("调度中心节点配置错误！编码{}对应的数据存在多条", number);
            return nodeConfigList.get(0);
        } else if (nodeConfigList.size() == 1) {
            return nodeConfigList.get(0);
        } else {
            return null;
        }
    }
}
