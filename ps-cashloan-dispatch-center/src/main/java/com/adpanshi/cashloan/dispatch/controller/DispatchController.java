package com.adpanshi.cashloan.dispatch.controller;

import com.adpanshi.cashloan.dispatch.run.bo.DispatchRunResponseBo;
import com.adpanshi.cashloan.dispatch.run.domain.DispatchRunDomain;
import com.adpanshi.cashloan.dispatch.run.enums.StatusCode;
import com.alibaba.fastjson.JSONObject;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(value = "adpanshi-dispatch-center", description = "调度中心")
@RestController
@RequestMapping("/adpanshi/dispatch")
public class DispatchController  {

    private static final Logger logger = LoggerFactory.getLogger(DispatchController.class);

    @Resource
    private DispatchRunDomain dispatchRunDomain;

    @ApiOperation("启动节点")
    @RequestMapping(value = "/run_node", method = RequestMethod.POST)
    public DispatchRunResponseBo runNode(@ApiParam("节点编码 字段名:userdata_id") Integer userdata_id,
                                         @ApiParam("节点编码 字段名:node_number") String node_number,
                                         @ApiParam("用户账号 字段名:user_mobile") String user_mobile,
                                         @ApiParam("用户账号 字段名:user_email") String user_email,
                                         @ApiParam("身份证号 字段名:user_idcard") String user_idcard,
                                         @ApiParam("用户姓名 字段名:user_name") String user_name,
                                         @ApiParam("设备指纹 字段名:equipment_fingerpints") String equipment_fingerpints,
                                         @ApiParam("请求参数json 字段名:request_params") String request_params) {

        logger.info("调用启动节点,请求参数:userdata_id={}, node_number={}, user_account={}, user_idcard={}, user_name={}, equipment_fingerpints={}, request_params={}",
                userdata_id, node_number, user_mobile, user_email, user_idcard, user_name, equipment_fingerpints, request_params);

        //入参校验
        if(StringUtils.isBlank(node_number) || StringUtils.isBlank(equipment_fingerpints)){

           return  DispatchRunResponseBo.error(StatusCode.PARAMS_WRONG);
        }
        Map map;
        if(StringUtils.isBlank(request_params)){
            map = new HashMap(16);
        }else{
            map = JSONObject.parseObject(request_params, Map.class);
        }

        return dispatchRunDomain.startNode(userdata_id, node_number, user_mobile, user_email, user_idcard, user_name,equipment_fingerpints, map);
    }
}

