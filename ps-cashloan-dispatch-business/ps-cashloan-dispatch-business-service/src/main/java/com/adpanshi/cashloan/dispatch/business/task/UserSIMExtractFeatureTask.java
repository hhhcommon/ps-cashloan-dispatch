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
 * Created by zsw on 2018/8/3 0003.
 */
@Service
public class UserSIMExtractFeatureTask implements Task {

    private static final Logger logger = LoggerFactory.getLogger(UserSIMExtractFeatureTask.class);

    @Resource
    private DispatchRunDomain dispatchRunDomain;
    @Resource
    private UserDataDomain userDataDomain;

    @Override
    public DispatchRunResponseBo run(Integer taskInstanceId) {
        logger.info("【用户SIM信息特征抽取 taskInstanceId={}】",taskInstanceId);

        TaskInstanceBo taskInstanceBo = null;
        try {
            taskInstanceBo = dispatchRunDomain.getTaskInstanceBo(taskInstanceId);

            JSONObject jsonObject = JSONObject.parseObject(taskInstanceBo.getParamsJson());

            //判断用户SIM变量特征是否为空,如果为空则返回异常
            List<ExtractFeatureApplyBo> applyBoList = new ArrayList<>();
            Boolean isExtractFeatureSuccess;
            List<Integer> simVariableIds = (List<Integer>)jsonObject.get(TaskParamConstant.USER_DATA_VARIABLE_ID_LIST);
            if(simVariableIds==null || simVariableIds.size() == 0){
                dispatchRunDomain.finishTaskInstance(taskInstanceId,taskInstanceBo.getParamsJson(), TaskStatus.FAIL);
                return DispatchRunResponseBo.error(StatusCode.FULL_MOXIE_SIM_INFO_FEATURE_FAIL);
            }else{
                //这里是抽取用户SIM信息特征
                applyBoList.add(new ExtractFeatureApplyBo(FeatureType.USER_FRAUD_MOXIESIM, simVariableIds));
                isExtractFeatureSuccess = userDataDomain.extractFeatures(taskInstanceBo.getUserDataId(), applyBoList);
            }

            if(isExtractFeatureSuccess){
                dispatchRunDomain.finishTaskInstance(taskInstanceId,taskInstanceBo.getParamsJson(), TaskStatus.SUCCESS);
                return DispatchRunResponseBo.success();
            }else{
                dispatchRunDomain.finishTaskInstance(taskInstanceId,taskInstanceBo.getParamsJson(), TaskStatus.FAIL);
                return DispatchRunResponseBo.error(StatusCode.FULL_MOXIE_SIM_INFO_FEATURE_FAIL);
            }
        } catch (Exception e) {
            logger.error("【用户SIM信息特征抽取异常，异常信息： 】",e);
            dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstanceBo == null?null:taskInstanceBo.getParamsJson(), TaskStatus.FAIL);
            return DispatchRunResponseBo.error(StatusCode.FULL_MOXIE_SIM_INFO_FEATURE_FAIL,e.getMessage());
        }

    }
}
