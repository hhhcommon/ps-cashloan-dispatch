package com.adpanshi.cashloan.dispatch.business.task;

import com.adpanshi.cashloan.data.thirdparty.pancard.bo.PanCardDataBo;
import com.adpanshi.cashloan.data.user.domain.UserDataDomain;
import com.adpanshi.cashloan.dispatch.business.constant.TaskParamConstant;
import com.adpanshi.cashloan.dispatch.run.bo.DispatchRunResponseBo;
import com.adpanshi.cashloan.dispatch.run.bo.TaskInstanceBo;
import com.adpanshi.cashloan.dispatch.run.domain.DispatchRunDomain;
import com.adpanshi.cashloan.dispatch.run.domain.Task;
import com.adpanshi.cashloan.dispatch.run.enums.StatusCode;
import com.adpanshi.cashloan.dispatch.run.enums.TaskStatus;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 盘卡原始数据提交
 * Created by zsw on 2018/8/3 0003.
 */
@Service
public class PanCardTask implements Task {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserBaseInfoTask.class);

    @Resource
    private DispatchRunDomain dispatchRunDomain;
    @Resource
    private UserDataDomain userDataDomain;


    @Override
    public DispatchRunResponseBo run(Integer taskInstanceId) {
        LOGGER.info("【提交盘卡信息 taskInstanceId={}】", taskInstanceId);

        TaskInstanceBo taskInstatnceBo = null;
        try {
            taskInstatnceBo = dispatchRunDomain.getTaskInstanceBo(taskInstanceId);
            JSONObject paramsJson = JSONObject.parseObject(taskInstatnceBo.getParamsJson());
            String requestParamsStr = paramsJson.getString(TaskParamConstant.THIRD_PARTY_REQUEST_PARAMJSON);
            String scriptMetaData =paramsJson.getString(TaskParamConstant.THIRD_PARTY_METADATA);
            PanCardDataBo panCardDataBo = null;
            if(!StringUtils.isBlank(scriptMetaData)){
                //如果传过来有历史数据则直接保留
                panCardDataBo= userDataDomain.fillPanCardInfoForOldData(taskInstatnceBo.getUserDataId(), scriptMetaData);
            }else{
                //如果传过来有历史数据则直接保留,否则获取第三方
                if (requestParamsStr == null) {
                    dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
                    return DispatchRunResponseBo.error(StatusCode.OTHER_ERROR, "需要的参数为空");
                }
                panCardDataBo= userDataDomain.fillPanCardInfo(taskInstatnceBo.getUserDataId(), requestParamsStr);
            }
            if (panCardDataBo != null) {
                JSONObject finishParams = StringUtils.isEmpty(taskInstatnceBo.getParamsJson())?new JSONObject():JSONObject.parseObject(taskInstatnceBo.getParamsJson());
                finishParams.put(TaskParamConstant.THIRD_PARTY_METADATA, JSONObject.toJSONString(panCardDataBo));
                dispatchRunDomain.finishTaskInstance(taskInstanceId, finishParams.toJSONString(), TaskStatus.SUCCESS);

                return DispatchRunResponseBo.success(panCardDataBo.getOriginalData());
            } else {
                dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
                return DispatchRunResponseBo.error(StatusCode.FULL_THIRDPARTY_PANCARD_INFO_FAIL);
            }
        } catch (Exception e) {
            LOGGER.error("【提交盘卡信息异常，异常信息： 】", e);
            dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo == null?null:taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
            return DispatchRunResponseBo.error(StatusCode.FULL_THIRDPARTY_PANCARD_INFO_FAIL, e.getMessage());
        }
    }

    public static void main(String[] args) {
        JSONObject param = new JSONObject();
        param.put("panNo", "BFYPK8619H");
        param.put("firstName", "aaa");
        param.put("lastName", "bbb");
        JSONObject result = new JSONObject();
        result.put("aaa", param.toJSONString());
        System.err.println(result.toJSONString());
    }
}
