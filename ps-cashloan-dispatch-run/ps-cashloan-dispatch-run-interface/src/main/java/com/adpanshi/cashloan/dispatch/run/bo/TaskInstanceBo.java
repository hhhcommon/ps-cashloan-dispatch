package com.adpanshi.cashloan.dispatch.run.bo;


import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.dispatch.run.enums.TaskStatus;

/**
 * 任务实例
 */
public class TaskInstanceBo implements java.io.Serializable ,BeanUtil.ConversionCustomizble{
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer nodeInstId;
    private Integer taskId;
    private String adapterCode;
    private Boolean isSync;
    private TaskStatus status;
    private Integer userDataId;
    private String userAccount;
    private String userIdCard;
    private String userName;
    private String deviceFingerprint;//tokenKey
    private String paramsJson;
    private String finishParamsJson;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNodeInstId() {
        return nodeInstId;
    }

    public void setNodeInstId(Integer nodeInstId) {
        this.nodeInstId = nodeInstId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getAdapterCode() {
        return adapterCode;
    }

    public void setAdapterCode(String adapterCode) {
        this.adapterCode = adapterCode;
    }

    public Boolean getIsSync() {
        return isSync;
    }

    public void setIsSync(Boolean sync) {
        isSync = sync;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Integer getUserDataId() {
        return userDataId;
    }

    public void setUserDataId(Integer userDataId) {
        this.userDataId = userDataId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserIdCard() {
        return userIdCard;
    }

    public void setUserIdCard(String userIdCard) {
        this.userIdCard = userIdCard;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeviceFingerprint() {
        return deviceFingerprint;
    }

    public void setDeviceFingerprint(String deviceFingerprint) {
        this.deviceFingerprint = deviceFingerprint;
    }

    public String getParamsJson() {
        return paramsJson;
    }

    public void setParamsJson(String paramsJson) {
        this.paramsJson = paramsJson;
    }

    public String getFinishParamsJson() {
        return finishParamsJson;
    }

    public void setFinishParamsJson(String finishParamsJson) {
        this.finishParamsJson = finishParamsJson;
    }

    @Override
    public void convertOthers(Object o) {
        Object status = BeanUtil.getPropValue(o, "status");
        if (status != null && status instanceof Integer) {
            this.setStatus(TaskStatus.valueOf((Integer) status));
        }
    }
}
