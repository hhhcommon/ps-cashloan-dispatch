package com.adpanshi.cashloan.dispatch.run.model;

import java.io.Serializable;
import java.util.Date;

public class TaskInstance implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cl_inst_task.FId
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cl_inst_task.FNodeInstId
     *
     * @mbg.generated
     */
    private Integer nodeInstId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cl_inst_task.FNodeId
     *
     * @mbg.generated
     */
    private Integer nodeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cl_inst_task.FTaskId
     *
     * @mbg.generated
     */
    private Integer taskId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cl_inst_task.FAdapterCode
     *
     * @mbg.generated
     */
    private String adapterCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cl_inst_task.FIsSync
     *
     * @mbg.generated
     */
    private Boolean isSync;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cl_inst_task.FStatus
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cl_inst_task.FUserDataId
     *
     * @mbg.generated
     */
    private Integer userDataId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cl_inst_task.FUserMobile
     *
     * @mbg.generated
     */
    private String userMobile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cl_inst_task.FUserEmail
     *
     * @mbg.generated
     */
    private String userEmail;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cl_inst_task.FUserIdCard
     *
     * @mbg.generated
     */
    private String userIdCard;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cl_inst_task.FUserName
     *
     * @mbg.generated
     */
    private String userName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cl_inst_task.FDeviceFingerprint
     *
     * @mbg.generated
     */
    private String deviceFingerprint;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cl_inst_task.FCreateTime
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cl_inst_task.FCreateUserId
     *
     * @mbg.generated
     */
    private Integer createUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cl_inst_task.FLastModifyTime
     *
     * @mbg.generated
     */
    private Date lastModifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cl_inst_task.FModifyUserId
     *
     * @mbg.generated
     */
    private Integer modifyUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cl_inst_task.FIsdelete
     *
     * @mbg.generated
     */
    private Boolean isdelete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cl_inst_task
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cl_inst_task.FId
     *
     * @return the value of cl_inst_task.FId
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cl_inst_task.FId
     *
     * @param id the value for cl_inst_task.FId
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cl_inst_task.FNodeInstId
     *
     * @return the value of cl_inst_task.FNodeInstId
     *
     * @mbg.generated
     */
    public Integer getNodeInstId() {
        return nodeInstId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cl_inst_task.FNodeInstId
     *
     * @param nodeInstId the value for cl_inst_task.FNodeInstId
     *
     * @mbg.generated
     */
    public void setNodeInstId(Integer nodeInstId) {
        this.nodeInstId = nodeInstId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cl_inst_task.FNodeId
     *
     * @return the value of cl_inst_task.FNodeId
     *
     * @mbg.generated
     */
    public Integer getNodeId() {
        return nodeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cl_inst_task.FNodeId
     *
     * @param nodeId the value for cl_inst_task.FNodeId
     *
     * @mbg.generated
     */
    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cl_inst_task.FTaskId
     *
     * @return the value of cl_inst_task.FTaskId
     *
     * @mbg.generated
     */
    public Integer getTaskId() {
        return taskId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cl_inst_task.FTaskId
     *
     * @param taskId the value for cl_inst_task.FTaskId
     *
     * @mbg.generated
     */
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cl_inst_task.FAdapterCode
     *
     * @return the value of cl_inst_task.FAdapterCode
     *
     * @mbg.generated
     */
    public String getAdapterCode() {
        return adapterCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cl_inst_task.FAdapterCode
     *
     * @param adapterCode the value for cl_inst_task.FAdapterCode
     *
     * @mbg.generated
     */
    public void setAdapterCode(String adapterCode) {
        this.adapterCode = adapterCode == null ? null : adapterCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cl_inst_task.FIsSync
     *
     * @return the value of cl_inst_task.FIsSync
     *
     * @mbg.generated
     */
    public Boolean getIsSync() {
        return isSync;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cl_inst_task.FIsSync
     *
     * @param isSync the value for cl_inst_task.FIsSync
     *
     * @mbg.generated
     */
    public void setIsSync(Boolean isSync) {
        this.isSync = isSync;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cl_inst_task.FStatus
     *
     * @return the value of cl_inst_task.FStatus
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cl_inst_task.FStatus
     *
     * @param status the value for cl_inst_task.FStatus
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cl_inst_task.FUserDataId
     *
     * @return the value of cl_inst_task.FUserDataId
     *
     * @mbg.generated
     */
    public Integer getUserDataId() {
        return userDataId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cl_inst_task.FUserDataId
     *
     * @param userDataId the value for cl_inst_task.FUserDataId
     *
     * @mbg.generated
     */
    public void setUserDataId(Integer userDataId) {
        this.userDataId = userDataId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cl_inst_task.FUserMobile
     *
     * @return the value of cl_inst_task.FUserMobile
     *
     * @mbg.generated
     */
    public String getUserMobile() {
        return userMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cl_inst_task.FUserMobile
     *
     * @param userMobile the value for cl_inst_task.FUserMobile
     *
     * @mbg.generated
     */
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cl_inst_task.FUserEmail
     *
     * @return the value of cl_inst_task.FUserEmail
     *
     * @mbg.generated
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cl_inst_task.FUserEmail
     *
     * @param userEmail the value for cl_inst_task.FUserEmail
     *
     * @mbg.generated
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cl_inst_task.FUserIdCard
     *
     * @return the value of cl_inst_task.FUserIdCard
     *
     * @mbg.generated
     */
    public String getUserIdCard() {
        return userIdCard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cl_inst_task.FUserIdCard
     *
     * @param userIdCard the value for cl_inst_task.FUserIdCard
     *
     * @mbg.generated
     */
    public void setUserIdCard(String userIdCard) {
        this.userIdCard = userIdCard == null ? null : userIdCard.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cl_inst_task.FUserName
     *
     * @return the value of cl_inst_task.FUserName
     *
     * @mbg.generated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cl_inst_task.FUserName
     *
     * @param userName the value for cl_inst_task.FUserName
     *
     * @mbg.generated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cl_inst_task.FDeviceFingerprint
     *
     * @return the value of cl_inst_task.FDeviceFingerprint
     *
     * @mbg.generated
     */
    public String getDeviceFingerprint() {
        return deviceFingerprint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cl_inst_task.FDeviceFingerprint
     *
     * @param deviceFingerprint the value for cl_inst_task.FDeviceFingerprint
     *
     * @mbg.generated
     */
    public void setDeviceFingerprint(String deviceFingerprint) {
        this.deviceFingerprint = deviceFingerprint == null ? null : deviceFingerprint.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cl_inst_task.FCreateTime
     *
     * @return the value of cl_inst_task.FCreateTime
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cl_inst_task.FCreateTime
     *
     * @param createTime the value for cl_inst_task.FCreateTime
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cl_inst_task.FCreateUserId
     *
     * @return the value of cl_inst_task.FCreateUserId
     *
     * @mbg.generated
     */
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cl_inst_task.FCreateUserId
     *
     * @param createUserId the value for cl_inst_task.FCreateUserId
     *
     * @mbg.generated
     */
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cl_inst_task.FLastModifyTime
     *
     * @return the value of cl_inst_task.FLastModifyTime
     *
     * @mbg.generated
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cl_inst_task.FLastModifyTime
     *
     * @param lastModifyTime the value for cl_inst_task.FLastModifyTime
     *
     * @mbg.generated
     */
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cl_inst_task.FModifyUserId
     *
     * @return the value of cl_inst_task.FModifyUserId
     *
     * @mbg.generated
     */
    public Integer getModifyUserId() {
        return modifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cl_inst_task.FModifyUserId
     *
     * @param modifyUserId the value for cl_inst_task.FModifyUserId
     *
     * @mbg.generated
     */
    public void setModifyUserId(Integer modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cl_inst_task.FIsdelete
     *
     * @return the value of cl_inst_task.FIsdelete
     *
     * @mbg.generated
     */
    public Boolean getIsdelete() {
        return isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cl_inst_task.FIsdelete
     *
     * @param isdelete the value for cl_inst_task.FIsdelete
     *
     * @mbg.generated
     */
    public void setIsdelete(Boolean isdelete) {
        this.isdelete = isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cl_inst_task
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TaskInstance other = (TaskInstance) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getNodeInstId() == null ? other.getNodeInstId() == null : this.getNodeInstId().equals(other.getNodeInstId()))
            && (this.getNodeId() == null ? other.getNodeId() == null : this.getNodeId().equals(other.getNodeId()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getAdapterCode() == null ? other.getAdapterCode() == null : this.getAdapterCode().equals(other.getAdapterCode()))
            && (this.getIsSync() == null ? other.getIsSync() == null : this.getIsSync().equals(other.getIsSync()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getUserDataId() == null ? other.getUserDataId() == null : this.getUserDataId().equals(other.getUserDataId()))
            && (this.getUserMobile() == null ? other.getUserMobile() == null : this.getUserMobile().equals(other.getUserMobile()))
            && (this.getUserEmail() == null ? other.getUserEmail() == null : this.getUserEmail().equals(other.getUserEmail()))
            && (this.getUserIdCard() == null ? other.getUserIdCard() == null : this.getUserIdCard().equals(other.getUserIdCard()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getDeviceFingerprint() == null ? other.getDeviceFingerprint() == null : this.getDeviceFingerprint().equals(other.getDeviceFingerprint()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getLastModifyTime() == null ? other.getLastModifyTime() == null : this.getLastModifyTime().equals(other.getLastModifyTime()))
            && (this.getModifyUserId() == null ? other.getModifyUserId() == null : this.getModifyUserId().equals(other.getModifyUserId()))
            && (this.getIsdelete() == null ? other.getIsdelete() == null : this.getIsdelete().equals(other.getIsdelete()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cl_inst_task
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getNodeInstId() == null) ? 0 : getNodeInstId().hashCode());
        result = prime * result + ((getNodeId() == null) ? 0 : getNodeId().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getAdapterCode() == null) ? 0 : getAdapterCode().hashCode());
        result = prime * result + ((getIsSync() == null) ? 0 : getIsSync().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getUserDataId() == null) ? 0 : getUserDataId().hashCode());
        result = prime * result + ((getUserMobile() == null) ? 0 : getUserMobile().hashCode());
        result = prime * result + ((getUserEmail() == null) ? 0 : getUserEmail().hashCode());
        result = prime * result + ((getUserIdCard() == null) ? 0 : getUserIdCard().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getDeviceFingerprint() == null) ? 0 : getDeviceFingerprint().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getLastModifyTime() == null) ? 0 : getLastModifyTime().hashCode());
        result = prime * result + ((getModifyUserId() == null) ? 0 : getModifyUserId().hashCode());
        result = prime * result + ((getIsdelete() == null) ? 0 : getIsdelete().hashCode());
        return result;
    }
}