package com.adpanshi.cashloan.dispatch.userdata.service;

import com.adpanshi.cashloan.data.user.bo.UserDataBo;
import com.adpanshi.cashloan.dispatch.userdata.mapper.UserMapper;
import com.adpanshi.cashloan.dispatch.userdata.model.User;
import com.adpanshi.cashloan.dispatch.userdata.model.UserExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsw on 2018/7/1 0001.
 */
@Service
public class DispatchUserDataService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DispatchUserDataService.class);

    @Resource
    private UserMapper userMapper;

    public User getUserByUserId(Integer userId) {
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(userId);
        List<User> userList = userMapper.selectByExample(example);
        if (userList.size() == 0) {
            return null;
        }
        if (userList.size() > 1) {
            LOGGER.error("调度中心用户数据错误:userDataId:{} 对应的数据有多条", userId);
        }
        return userList.get(0);
    }
}
