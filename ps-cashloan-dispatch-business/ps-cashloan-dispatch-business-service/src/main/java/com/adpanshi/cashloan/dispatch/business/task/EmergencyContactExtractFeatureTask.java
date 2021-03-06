package com.adpanshi.cashloan.dispatch.business.task;

import com.adpanshi.cashloan.data.feature.enums.FeatureType;
import com.adpanshi.cashloan.data.user.bo.ExtractFeatureApplyBo;
import com.adpanshi.cashloan.data.user.domain.UserDataDomain;
import com.adpanshi.cashloan.dispatch.business.constant.TaskParamConstant;
import com.adpanshi.cashloan.dispatch.run.bo.DispatchRunResponseBo;
import com.adpanshi.cashloan.dispatch.run.bo.TaskInstanceBo;
import com.adpanshi.cashloan.dispatch.run.domain.DispatchRunDomain;
import com.adpanshi.cashloan.dispatch.run.domain.Task;
import com.adpanshi.cashloan.dispatch.run.enums.StatusCode;
import com.adpanshi.cashloan.dispatch.run.enums.TaskStatus;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fish_coder
 * @Title: EmergencyContactExtractFeatureTask
 * @ProjectName fenqidai-dubbo
 * @Description: TODO
 * @date 2018/8/1017:35
 */
@Service
public class EmergencyContactExtractFeatureTask implements Task {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppApplicationExtractFeatureTask.class);

    @Resource
    private DispatchRunDomain dispatchRunDomain;
    @Resource
    private UserDataDomain userDataDomain;

    @Override
    public DispatchRunResponseBo run(Integer taskInstanceId) {
        LOGGER.info("【APP紧急联系人特征抽取 taskInstanceId={}】",taskInstanceId);

        TaskInstanceBo taskInstanceBo = null;
        try {
            taskInstanceBo = dispatchRunDomain.getTaskInstanceBo(taskInstanceId);

            JSONObject jsonObject = JSONObject.parseObject(taskInstanceBo.getParamsJson());

            if(jsonObject.get(TaskParamConstant.USER_DATA_VARIABLE_ID_LIST)==null){
                dispatchRunDomain.finishTaskInstance(taskInstanceId,taskInstanceBo.getParamsJson(), TaskStatus.FAIL);
                return DispatchRunResponseBo.error(StatusCode.FULL_EMERGENCY_INFO_FEATURE_FAIL);
            }
            List<Integer> userVariableIds = (List<Integer>)jsonObject.get(TaskParamConstant.USER_DATA_VARIABLE_ID_LIST);

            List<ExtractFeatureApplyBo> applyBoList = new ArrayList<>();

            Boolean isExtractFeatureSuccess;
            if(userVariableIds == null || userVariableIds.size() == 0){
                dispatchRunDomain.finishTaskInstance(taskInstanceId,taskInstanceBo.getParamsJson(), TaskStatus.FAIL);
                return DispatchRunResponseBo.error(StatusCode.FULL_EMERGENCY_INFO_FEATURE_FAIL);
            }else{
                applyBoList.add(new ExtractFeatureApplyBo(FeatureType.USER_BASICINFO_FASTTOUCH, userVariableIds));
//                applyBoList.add(new ExtractFeatureApplyBo(FeatureType.USER_BASICINFO_FASTTOUCH, userVariableIds));

                isExtractFeatureSuccess = userDataDomain.extractFeatures(taskInstanceBo.getUserDataId(), applyBoList);
            }


            if(isExtractFeatureSuccess){
                dispatchRunDomain.finishTaskInstance(taskInstanceId,taskInstanceBo.getParamsJson(), TaskStatus.SUCCESS);
                return DispatchRunResponseBo.success();
            }else{
                dispatchRunDomain.finishTaskInstance(taskInstanceId,taskInstanceBo.getParamsJson(), TaskStatus.FAIL);
                return DispatchRunResponseBo.error(StatusCode.FULL_APPLICATION_INFO_FEATURE_FAIL);
            }
        } catch (Exception e) {
            LOGGER.error("【APP紧急联系人特征抽取异常，异常信息： 】",e);
            dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstanceBo == null?null:taskInstanceBo.getParamsJson(), TaskStatus.FAIL);
            return DispatchRunResponseBo.error(StatusCode.FULL_APPLICATION_INFO_FEATURE_FAIL,e.getMessage());
        }

    }
}
