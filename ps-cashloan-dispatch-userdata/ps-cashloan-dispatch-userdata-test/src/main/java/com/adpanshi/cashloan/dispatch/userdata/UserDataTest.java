package com.adpanshi.cashloan.dispatch.userdata;

import com.adpanshi.cashloan.data.user.bo.UserDataBo;
import com.adpanshi.cashloan.dispatch.userdata.domain.DispatchUserDataDomain;
import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.Test;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public class UserDataTest {

    private DispatchUserDataDomain remote = RemoteFactory.getRemote(DispatchUserDataDomain.class, "1.0.0");

    @Test
    public void getUserByUserId(){
//         remote(29);
    }



}
