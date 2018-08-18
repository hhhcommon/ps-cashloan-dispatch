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
import java.io.*;

/**
 * 磨盒社交
 * Created by zsw on 2018/8/3 0003.
 */
@Service
public class UserSNSTask implements Task {
    private final static Logger logger = LoggerFactory.getLogger(UserSIMTask.class);

    @Resource
    private DispatchRunDomain dispatchRunDomain;

    @Resource
    private UserDataDomain userDataDomain;

    @Override
    public DispatchRunResponseBo run(Integer taskInstanceId) {
        logger.info("开始执行魔蝎SNS任务,taskInstanceId:{}", taskInstanceId);
        TaskInstanceBo taskInstatnceBo = null;
        try {
            taskInstatnceBo = dispatchRunDomain.getTaskInstanceBo(taskInstanceId);
            JSONObject paramsJson = JSONObject.parseObject(taskInstatnceBo.getParamsJson());
            String scriptMetaData=paramsJson.getString(TaskParamConstant.THIRD_PARTY_METADATA);
            String moxieSNSMetaData=null;
            if (StringUtils.isBlank(scriptMetaData)){
                if (!paramsJson.containsKey(TaskParamConstant.THIRD_PARTY_REQUEST_PARAMJSON)) {
                    dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
                    return DispatchRunResponseBo.error(StatusCode.OTHER_ERROR, "需要的参数为空");
                }
                moxieSNSMetaData = userDataDomain.getMoxieSNSInfoFromThirdParty(paramsJson.getString(TaskParamConstant.THIRD_PARTY_REQUEST_PARAMJSON));
//            String moxieSNSMetaData = readTxt("C:\\Users\\Administrator\\Desktop\\sns.txt"); // TODO 测试用
            }
            if (StringUtil.isBlank(moxieSNSMetaData)) {
                dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
                return DispatchRunResponseBo.error(StatusCode.FULL_MOXIE_SNS_INFO_FAIL);
            }
            Integer dataId = userDataDomain.fillSNSInfo(taskInstatnceBo.getUserDataId(), moxieSNSMetaData);
            if (dataId != null) {
                JSONObject finishParams = new JSONObject();
                finishParams.put(TaskParamConstant.USER_DATA_METADATA_ID, dataId);
                dispatchRunDomain.finishTaskInstance(taskInstanceId, finishParams.toJSONString(), TaskStatus.SUCCESS);
                return DispatchRunResponseBo.success();
            } else {
                dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
                return DispatchRunResponseBo.error(StatusCode.FULL_MOXIE_SNS_INFO_FAIL);
            }
        } catch (Exception e) {
            logger.error("魔蝎SNS任务异常，异常信息：", e);
            dispatchRunDomain.finishTaskInstance(taskInstanceId, taskInstatnceBo == null ? null : taskInstatnceBo.getParamsJson(), TaskStatus.FAIL);
            return DispatchRunResponseBo.error(StatusCode.FULL_MOXIE_SNS_INFO_FAIL, e.getMessage());
        }
    }

    private String readTxt(String pathname) {
        StringBuffer sb = new StringBuffer();
        try {
            File filename = new File(pathname); // 要读取以上路径的input.txt文件
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line;
            //网友推荐更加简洁的写法

            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                sb.append(line.trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
