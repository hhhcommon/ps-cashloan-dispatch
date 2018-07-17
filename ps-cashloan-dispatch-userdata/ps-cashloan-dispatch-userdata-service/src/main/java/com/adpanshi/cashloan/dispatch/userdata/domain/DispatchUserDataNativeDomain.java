package com.adpanshi.cashloan.dispatch.userdata.domain;

import com.adpanshi.cashloan.data.user.bo.UserDataBo;
import com.adpanshi.cashloan.dispatch.userdata.exception.DispatchUserDataException;
import com.adpanshi.cashloan.dispatch.userdata.model.User;
import com.adpanshi.cashloan.dispatch.userdata.service.DispatchUserDataService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 调度中心用户数据管理
 * Created by zsw on 2017/9/1.
 */
@Service("dispatchUserDataDomain")
public class DispatchUserDataNativeDomain implements DispatchUserDataDomain {
    private static final Logger LOGGER = LoggerFactory.getLogger(DispatchUserDataNativeDomain.class);

    @Resource
    private DispatchUserDataService dispatchUserDataService;

//    /**
//     * 获取用户数据
//     */
//    public UserDataBo getUserDataBo(Integer userId) {
//        User user = dispatchUserDataService.getUserByUserId(userId);
//        System.out.println(JSONObject.toJSONString(user));
//        UserDataBo userDataBo = null;
//        return userDataBo;
//    }
//
//    /**
//     * 更新用户数据
//     */
//    @Override
//    public void modifyUserData(String aadhaarNo, String account, String deviceFingerprint, String name) {
//
//    }
//
//    @Override
//    public void sealUp(Integer userDataId) {
//
//    }

}
