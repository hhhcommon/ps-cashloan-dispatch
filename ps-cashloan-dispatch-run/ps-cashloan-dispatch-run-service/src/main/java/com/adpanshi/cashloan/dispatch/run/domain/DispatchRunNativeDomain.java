package com.adpanshi.cashloan.dispatch.run.domain;

import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.data.common.exception.BusinessException;
import com.adpanshi.cashloan.dispatch.config.bo.NodeConfigBo;
import com.adpanshi.cashloan.dispatch.config.bo.TaskConfigBo;
import com.adpanshi.cashloan.dispatch.config.domain.DispatchConfigDomain;
import com.adpanshi.cashloan.dispatch.run.bo.DispatchRunResponseBo;
import com.adpanshi.cashloan.dispatch.run.bo.TaskInstanceBo;
import com.adpanshi.cashloan.dispatch.run.enums.NodeStatus;
import com.adpanshi.cashloan.dispatch.run.enums.StatusCode;
import com.adpanshi.cashloan.dispatch.run.enums.TaskStatus;
import com.adpanshi.cashloan.dispatch.run.factory.TaskFactory;
import com.adpanshi.cashloan.dispatch.run.model.NodeInstance;
import com.adpanshi.cashloan.dispatch.run.model.TaskInstance;
import com.adpanshi.cashloan.dispatch.run.model.TaskInstanceWithBLOBs;
import com.adpanshi.cashloan.dispatch.run.service.NodeInstanceService;
import com.adpanshi.cashloan.dispatch.run.service.TaskInstanceService;
import com.adpanshi.cashloan.jms.task.bo.JmsResponseBo;
import com.adpanshi.cashloan.jms.task.domain.JmsActiveTaskDomain;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tool.util.StringUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zsw on 2018/6/22 0022.
 */
@Service("dispatchRunDomain")
public class DispatchRunNativeDomain implements DispatchRunDomain {
    private static final Logger LOGGER = LoggerFactory.getLogger(DispatchRunNativeDomain.class);

    @Resource
    private DispatchConfigDomain dispatchConfigDomain;
    @Resource
    private NodeInstanceService nodeInstanceService;
    @Resource
    private TaskInstanceService taskInstanceService;
    @Resource
    private TaskFactory taskFactory;
    @Resource
    private JmsActiveTaskDomain jmsActiveTaskDomain;

    @Override
    public DispatchRunResponseBo startNode(Integer userDataId, String nodeNumber, String mobile, String email, String aadhaarNo, String userName, String deviceFingerprint, Map<String, String> params) {
        NodeConfigBo nodeConfig = dispatchConfigDomain.getNodeByNumber(nodeNumber);
        //同步任务
        TaskConfigBo syncTask = dispatchConfigDomain.getSyncTask(nodeNumber);
        //异步任务
        List<TaskConfigBo> asyncTaskList = dispatchConfigDomain.findNoParentAsyncTask(nodeNumber);
        if (nodeConfig == null) {
            return DispatchRunResponseBo.error(StatusCode.OTHER_ERROR);
        }
        if (StringUtil.isBlank(mobile) || StringUtil.isBlank(email)) {
            return DispatchRunResponseBo.error(StatusCode.PARAMS_WRONG, "手机号或邮箱不能为空");
        }
//        if (deviceFingerprint == null) {
//            return DispatchRunResponseBo.error(StatusCode.PARAMS_WRONG, "设备指纹不能为空");
//        }
        //创建
        NodeInstance nodeInstance = nodeInstanceService.create(nodeConfig.getId(), userDataId, mobile, email, aadhaarNo, userName, deviceFingerprint, params);
        //调用同步任务
        DispatchRunResponseBo responseBo = null;
        if (syncTask != null) {
            TaskInstanceWithBLOBs taskInst = taskInstanceService.create(syncTask.getAdapterCode(), nodeInstance.getId(), nodeConfig.getId(), syncTask.getId(), userDataId, mobile, email, aadhaarNo, userName, deviceFingerprint, JSONObject.toJSONString(params), true);
            LOGGER.info("成功创建任务实例,id:{}", taskInst.getId());
            responseBo = runTask(taskInst.getId());
            if (!StatusCode.SUCCESS.getValue().equals(responseBo.getRet_code())) {
                //任务必须成功但执行失败，直接返回结果
                responseBo.setNodeInstId(nodeInstance.getId());
                return responseBo;
            }
        }
        //调用异步任务
        for (TaskConfigBo asyncTask : asyncTaskList) {
            try {
                //添加
                TaskInstanceWithBLOBs asyncTaskInst = taskInstanceService.create(asyncTask.getAdapterCode(), nodeInstance.getId(), nodeConfig.getId(), asyncTask.getId(), userDataId, mobile, email, aadhaarNo, userName, deviceFingerprint, JSONObject.toJSONString(params), false);
                LOGGER.info("成功创建任务实例,id:{}", asyncTaskInst.getId());
                //调用
//                Map<String,Object> map=new HashMap<>(16);
//                map.put("taskId",asyncTaskInst.getId());
//                JmsResponseBo jmsResponseBo = jmsActiveTaskDomain.addDispathTask(map);
//                LOGGER.info("发送jms消息结果,taskId="+asyncTaskInst.getId()+",jmsResultCode="+jmsResponseBo.getRet_code()+",jmsResultMsg="+jmsResponseBo.getRet_msg());
//                LOGGER.info("成功异步发起执行任务实例,id:{}", asyncTaskInst.getId());
                runTask(asyncTaskInst.getId());// TODO 测试用
            } catch (Exception e) {
                LOGGER.error("调度中心调用事件异常", e);
            }
        }
        if (responseBo != null) {
            responseBo.setNodeInstId(nodeInstance.getId());
            return responseBo;
        } else {
            return DispatchRunResponseBo.success(nodeInstance.getId());
        }
    }

    @Override
    public TaskInstanceBo getTaskInstanceBo(Integer taskInstatnceId) {
        return BeanUtil.convert(taskInstanceService.get(taskInstatnceId), TaskInstanceBo.class);
    }

    @Override
    public void addParam(Integer taskInstId, Map<String, Object> map) {
        taskInstanceService.addParam(taskInstId, map);
    }

    @Override
    public void finishTaskInstance(Integer taskInstatnceId, String params, TaskStatus status) {
        if (TaskStatus.SUCCESS.equals(status)) {
            TaskInstanceWithBLOBs taskInst = taskInstanceService.get(taskInstatnceId);
            TaskConfigBo task = dispatchConfigDomain.getTask(taskInst.getTaskId());
            if (task.getIsFinalTask()) {
                nodeInstanceService.finish(taskInst.getNodeInstId(), NodeStatus.SUCCESS);
//                dispatchUserDataDomain.sealUp(taskInst.getUserDataId());
            }
        }
        taskInstanceService.finish(taskInstatnceId, params, status);
    }

    /**
     * 调用任务
     *
     * @param taskInstId 任务实例ID
     */
    @Override
    public DispatchRunResponseBo runTask(Integer taskInstId) {
        Integer userDataId = taskInstanceService.getUserDataId(taskInstId);
        synchronized (("RUNTASK_" + userDataId).intern()) {
            TaskInstanceWithBLOBs taskInstance = taskInstanceService.get(taskInstId);
            if (!TaskStatus.INIT.getValue().equals(taskInstance.getStatus()) && !TaskStatus.FAIL.getValue().equals(taskInstance.getStatus())) {
                throw new BusinessException("任务重复调用,id=" + taskInstId);
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
            //更新任务信息
            taskInstance = taskInstanceService.get(taskInstId);
            //调用子任务
            List<TaskConfigBo> sonTaskConfigList = dispatchConfigDomain.findTaskByParentId(taskInstance.getTaskId());
            for (TaskConfigBo sonTaskConfigBo : sonTaskConfigList) {
                if (isTaskEnable(taskInstance.getNodeInstId(), sonTaskConfigBo.getId())) {
                    TaskInstance sonTaskInst = null;
                    try {
                        //添加
                        sonTaskInst = taskInstanceService.create(sonTaskConfigBo.getAdapterCode(), taskInstance.getNodeInstId(), taskInstance.getNodeId(), sonTaskConfigBo.getId(), taskInstance.getUserDataId(), taskInstance.getUserMobile(), taskInstance.getUserEmail(), taskInstance.getUserIdCard(), taskInstance.getUserName(), taskInstance.getDeviceFingerprint(), taskInstance.getFinishParamsJson(), false);
                        LOGGER.info("成功创建任务实例,id:{}", sonTaskConfigBo.getId());
                        if (taskInstance.getIsSync()) {
                            //调用
//                            Map<String,Object> map=new HashMap<>(16);
//                            map.put("taskId",sonTaskInst.getId());
//                            JmsResponseBo jmsResponseBo = jmsActiveTaskDomain.addDispathTask(map);
//                            LOGGER.info("发送jms消息结果,taskId="+sonTaskConfigBo.getId()+",jmsResultCode="+jmsResponseBo.getRet_code()+",jmsResultMsg="+jmsResponseBo.getRet_msg());
                            runTask(sonTaskInst.getId());// TODO 测试用
                            //如果是同步任务,后续发异步,不影响调用者
                            LOGGER.info("成功异步发起执行任务实例,id:{}", sonTaskConfigBo.getId());

                        } else {
                            //如果是异步任务,后续同步执行,增快处理速度
                            runTask(sonTaskInst.getId());
                            LOGGER.info("同步发起执行任务实例成功,id:{}", sonTaskConfigBo.getId());
                        }
                    } catch (Exception e) {
                        LOGGER.error("异常执行任务实例,id:{}", (sonTaskInst == null ? null : sonTaskInst.getId()), e);
                    }
                }
            }
            return responseBo;
        }
    }

    @Override
    public DispatchRunResponseBo runByTaskId(Integer taskInstId) {
        LOGGER.info("接收到jms回调任务,taskId="+taskInstId);
        return runTask(taskInstId);
    }

    /**
     * 查找上级任务实例List
     */
    @Override
    public List<TaskInstanceBo> findParentTaskInstByTaskInstId(Integer taskInst) {
        if (taskInst == null) {
            return null;
        }
        List<TaskInstanceWithBLOBs> parentTaskAndTaskId = taskInstanceService.findParentTaskInstByTaskInstId(taskInst);
        if (parentTaskAndTaskId == null || parentTaskAndTaskId.size() == 0) {
            return null;
        }
        return BeanUtil.convertList(parentTaskAndTaskId, TaskInstanceBo.class);

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
    public TaskInstanceBo findDecisionTreeTaskById(Integer userDataId) {
        if (userDataId == null) {
            return null;
        }

        TaskInstanceWithBLOBs taskInstanceWithBLOBs = taskInstanceService.findDecisionTreeTaskById("invokeLoanDecisionTreeTask", userDataId);

        return BeanUtil.convert(taskInstanceWithBLOBs, TaskInstanceBo.class);
    }

}
