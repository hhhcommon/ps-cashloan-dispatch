package com.adpanshi.cashloan.dispatch.business.task;

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
import tool.util.StringUtil;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Created by zsw on 2018/8/3 0003.
 */
@Service
public class EquifaxTask implements Task {
    private static final Logger LOGGER = LoggerFactory.getLogger(EquifaxExtractFeatureTask.class);
    @Resource
    private DispatchRunDomain dispatchRunDomain;
    @Resource
    private UserDataDomain userDataDomain;
    @Override
    public DispatchRunResponseBo run(java.lang.Integer taskInstanceId) {
        TaskInstanceBo taskInstatnceBo = null;
        try {
            taskInstatnceBo = dispatchRunDomain.getTaskInstanceBo(taskInstanceId);
            JSONObject paramsJson = JSONObject.parseObject(taskInstatnceBo.getParamsJson());
            String paramsStr = paramsJson.getString(TaskParamConstant.THIRD_PARTY_REQUEST_PARAMJSON);
            String scriptMetaData = paramsJson.getString(TaskParamConstant.THIRD_PARTY_METADATA);
            String metaData = null;
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            //发送接口初始化日志记录 TODO 杜绝使用魔法值-状态值
            userDataDomain.saveEquifaxCreditReportLog(taskInstatnceBo.getUserDataId(),"0",uuid,paramsStr,null);
            if (StringUtil.isBlank(scriptMetaData)) {
                if (StringUtil.isBlank(paramsStr)) {
                    dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
                    return DispatchRunResponseBo.error(StatusCode.OTHER_ERROR, "需要的参数为空");
                }
                //发送接口
                metaData = userDataDomain.getEquifaxCreditReportMetaData(paramsStr);
            }
            //发送接口结束日志记录 TODO 杜绝使用魔法值-状态值
            userDataDomain.saveEquifaxCreditReportLog(taskInstatnceBo.getUserDataId(),"1",uuid,null,metaData);
            if (StringUtil.isBlank(metaData)) {
                dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
                return DispatchRunResponseBo.error(StatusCode.FULL_EQUIFAX_CREDITREPORT_INFO_FAIL);
            }else if(metaData.contains("message")){
                //获取信用报告失败
                dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
                return DispatchRunResponseBo.error(StatusCode.FULL_EQUIFAX_CREDITREPORT_INFO_FAIL);
            }
            Integer dataId = userDataDomain.fillEquifaxCreditReportInfo(taskInstatnceBo.getUserDataId(), uuid, paramsStr, metaData);
            if (dataId != null) {
                JSONObject finishParams = new JSONObject();
                finishParams.put(TaskParamConstant.USER_DATA_METADATA_ID, dataId);
                dispatchRunDomain.finishTaskInstance(taskInstanceId, finishParams.toJSONString(), TaskStatus.SUCCESS);

                return DispatchRunResponseBo.success();
            } else {
                dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
                return DispatchRunResponseBo.error(StatusCode.FULL_EQUIFAX_CREDITREPORT_INFO_FAIL);
            }
        } catch (Exception e) {
            LOGGER.error("【提交信用报告信息异常，异常信息： 】", e);
            dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo == null?null:taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
            return DispatchRunResponseBo.error(StatusCode.FULL_EQUIFAX_CREDITREPORT_INFO_FAIL, e.getMessage());
        }
    }
}
