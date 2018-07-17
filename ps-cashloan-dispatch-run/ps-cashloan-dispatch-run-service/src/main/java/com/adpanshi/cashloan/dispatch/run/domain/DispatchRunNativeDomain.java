package com.adpanshi.cashloan.dispatch.run.domain;

import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.dispatch.config.bo.TaskConfigBo;
import com.adpanshi.cashloan.dispatch.config.domain.DispatchConfigDomain;
import com.adpanshi.cashloan.dispatch.run.bo.DispatchRunResponseBo;
import com.adpanshi.cashloan.dispatch.run.bo.TaskInstanceBo;
import com.adpanshi.cashloan.dispatch.run.enums.StatusCode;
import com.adpanshi.cashloan.dispatch.run.enums.TaskStatus;
import com.adpanshi.cashloan.dispatch.run.exception.DispatchException;
import com.adpanshi.cashloan.dispatch.run.factory.TaskFactory;
import com.adpanshi.cashloan.dispatch.run.model.TaskInstance;
import com.adpanshi.cashloan.dispatch.run.model.TaskInstanceWithBLOBs;
import com.adpanshi.cashloan.dispatch.run.service.TaskInstanceService;
import com.adpanshi.cashloan.dispatch.userdata.domain.DispatchUserDataDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zsw on 2018/6/22 0022.
 */
@Service("dispatchRunDomain")
public class DispatchRunNativeDomain implements DispatchRunDomain {

    private static Logger LOGGER = LoggerFactory.getLogger(DispatchRunNativeDomain.class);

    @Resource
    private TaskInstanceService taskInstanceService;
    @Resource
    private DispatchConfigDomain dispatchConfigDomain;
    @Resource
    private DispatchUserDataDomain dispatchUserDataDomain;
    @Resource
    private TaskFactory taskFactory;

    public DispatchRunResponseBo runTask(Integer taskInstId) {
        Integer userDataId = taskInstanceService.getUserDataId(taskInstId);
        synchronized (("RUNTASK_" + userDataId).intern()) {
            TaskInstanceWithBLOBs taskInstance = taskInstanceService.get(taskInstId);
            if (!TaskStatus.INIT.getValue().equals(taskInstance.getStatus())) {
                throw new DispatchException("任务重复调用,id=" + taskInstId);
            }
            Task task = taskFactory.getTask(taskInstance.getAdapterCode());
            LOGGER.info("开始运行任务实例,id:{}", taskInstId);
            DispatchRunResponseBo responseBo;
            try {
                responseBo = task.run(taskInstId);
                LOGGER.info("完成运行任务实例,id:{}", taskInstId);
            } catch (Exception e) {
                LOGGER.error("异常运行任务实例,id:{}", taskInstId);
                finishTaskInstance(taskInstId, taskInstance.getParamsJson(), TaskStatus.FAIL);
                responseBo = DispatchRunResponseBo.error(StatusCode.OTHER_ERROR, e.getMessage());
            }
            if (!StatusCode.SUCCESS.getValue().equals(responseBo.getRet_code()) && !StatusCode.SKIP_TASK.getValue().equals(responseBo.getRet_code())) {
                return responseBo;
            }
            taskInstance = taskInstanceService.get(taskInstId);//更新任务信息
            //调用子任务
            List<TaskConfigBo> sonTaskConfigList = dispatchConfigDomain.findTaskByParentId(taskInstance.getTaskId());
            for (TaskConfigBo sonTaskConfigBo : sonTaskConfigList) {
                if (isTaskEnable(taskInstance.getNodeInstId(), sonTaskConfigBo.getId())) {
                    TaskInstance sonTaskInst = null;
                    try {
                        //添加
                        sonTaskInst = taskInstanceService.create(sonTaskConfigBo.getAdapterCode(), taskInstance.getNodeInstId(), taskInstance.getNodeId(), sonTaskConfigBo.getId(), taskInstance.getUserDataId(), taskInstance.getUserAccount(), taskInstance.getUserIdCard(), taskInstance.getUserName(), taskInstance.getDeviceFingerprint(), taskInstance.getFinishParamsJson(), false, sonTaskConfigBo.getIsPause());
                        LOGGER.info("成功创建任务实例,id:{}", sonTaskConfigBo.getId());
                        if (sonTaskInst.getIsPause() == null || !sonTaskInst.getIsPause()) {
                            //调用
                            if (taskInstance.getIsSync()) {
                                //如果是同步任务,后续发异步,不影响调用者
//                                eventBasiceMessageProducer.send(DispatchConstatnt.BizDefineCode, DispatchConstatnt.NodeDefineCode, sonTaskInst.getId());
                                LOGGER.info("成功异步发起执行任务实例,id:{}", sonTaskConfigBo.getId());
                            } else {
                                //如果是异步任务,后续同步执行,增快处理速度
                                runTask(sonTaskInst.getId());//TODO 测试用
                                LOGGER.info("同步发起执行任务实例成功,id:{}", sonTaskConfigBo.getId());//TODO 测试用
                            }
                        } else {
                            LOGGER.info("暂停执行任务实例,id:{}", sonTaskConfigBo.getId());
                        }
                    } catch (Exception e) {
                        LOGGER.error("异常执行任务实例,id:{}", (sonTaskInst == null ? null : sonTaskInst.getId()), e);
                    }
                }
            }
            return responseBo;
        }
    }

    /**
     * 判断一个节点下的任务是否可以开启,即父级任务是否已经完成或者跳过
     *
     * @param nodeInstanceId 节点实例
     * @param taskId         任务ID
     */
    private boolean isTaskEnable(Integer nodeInstanceId, Integer taskId) {
        List<TaskConfigBo> parentTaskList = dispatchConfigDomain.findTaskBySonId(taskId);
        for (TaskConfigBo taskConfigBo : parentTaskList) {
            List<TaskInstanceWithBLOBs> taskInstList = taskInstanceService.findPassByNodeInstIdAndTaskId(nodeInstanceId, taskConfigBo.getId());
            if (taskInstList.size() == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void finishTaskInstance(Integer taskInstatnceId, String params, TaskStatus status) {
        if (TaskStatus.SUCCESS.equals(status)) {
            TaskInstanceWithBLOBs taskInst = taskInstanceService.get(taskInstatnceId);
            TaskConfigBo task = dispatchConfigDomain.getTask(taskInst.getTaskId());
//            dispatchUserDataDomain.modifyUserData(taskInst.getUserIdCard(), taskInst.getUserAccount(), taskInst.getDeviceFingerprint(), taskInst.getUserName());
            if (task.getIsFinalTask()) {
//                dispatchUserDataDomain.sealUp(taskInst.getUserDataId());
            }
        }
        taskInstanceService.finish(taskInstatnceId, params, status);
    }

    @Override
    public TaskInstanceBo getTaskInstanceBo(Integer taskInstatnceId) {
        return BeanUtil.convert(taskInstanceService.get(taskInstatnceId), TaskInstanceBo.class);
    }
}
