package com.adpanshi.cashloan.dispatch.business.task;

import com.adpanshi.cashloan.data.common.enums.ChannelBizType;
import com.adpanshi.cashloan.data.common.enums.ChannelType;
import com.adpanshi.cashloan.data.user.bo.UserVariableBo;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsw on 2018/8/3 0003.
 */
@Service
public class UserSNSExtractVariableTask implements Task {

    private static final Logger logger = LoggerFactory.getLogger(UserSIMExtractVariableTask.class);

    @Resource
    private DispatchRunDomain dispatchRunDomain;
    @Resource
    private UserDataDomain userDataDomain;

    @Override
    public DispatchRunResponseBo run(Integer taskInstanceId) {

        logger.info("【用户SNS信息变量抽取 taskInstanceId={}】", taskInstanceId );

        TaskInstanceBo taskInstanceBo = null;
        try {
            taskInstanceBo = dispatchRunDomain.getTaskInstanceBo(taskInstanceId);

            JSONObject jsonObject = JSONObject.parseObject(taskInstanceBo.getParamsJson());
            //抽取用户SIM信息变量(这里根据用户SIM原始数据插入,)
            List<UserVariableBo> userVariableBoList = userDataDomain.extractVariable(taskInstanceBo.getUserDataId(), ChannelType.MOXIESNS,
                    ChannelBizType.MOXIE_SNS_INFO, jsonObject.getInteger(TaskParamConstant.USER_DATA_METADATA_ID));

            if(userVariableBoList.size() > 0){
                List<Integer> userVariableIds = new ArrayList<>();
                for (UserVariableBo userVariableBo : userVariableBoList) {
                    Integer variableDataId = userVariableBo.getVariableDataId();
                    userVariableIds.add(variableDataId);
                }

                JSONObject finishParams = StringUtils.isEmpty(taskInstanceBo.getParamsJson()) ? new JSONObject() : JSONObject.parseObject(taskInstanceBo.getParamsJson());
                finishParams.put(TaskParamConstant.USER_DATA_VARIABLE_ID_LIST, userVariableIds);

                dispatchRunDomain.finishTaskInstance(taskInstanceId, finishParams.toJSONString(), TaskStatus.SUCCESS);
                return DispatchRunResponseBo.success();
            }else{
                dispatchRunDomain.finishTaskInstance(taskInstanceId,taskInstanceBo.getParamsJson(), TaskStatus.FAIL);
                return DispatchRunResponseBo.error(StatusCode.FULL_MOXIE_SNS_INFO_VARIABLE_FAIL);
            }
        } catch (Exception e) {
            logger.error("【用户SNS信息变量抽取异常，异常信息： 】",e);
            dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstanceBo == null?null:taskInstanceBo.getParamsJson(), TaskStatus.FAIL);
            return DispatchRunResponseBo.error(StatusCode.FULL_MOXIE_SNS_INFO_VARIABLE_FAIL,e.getMessage());
        }
    }
}
