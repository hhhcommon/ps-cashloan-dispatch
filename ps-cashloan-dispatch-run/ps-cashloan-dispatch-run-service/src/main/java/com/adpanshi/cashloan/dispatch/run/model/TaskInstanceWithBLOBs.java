package com.adpanshi.cashloan.dispatch.run.model;

import java.io.Serializable;

public class TaskInstanceWithBLOBs extends TaskInstance implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cl_inst_task.FParamsJson
     *
     * @mbg.generated
     */
    private String paramsJson;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cl_inst_task.FFinishParamsJson
     *
     * @mbg.generated
     */
    private String finishParamsJson;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cl_inst_task
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cl_inst_task.FParamsJson
     *
     * @return the value of cl_inst_task.FParamsJson
     *
     * @mbg.generated
     */
    public String getParamsJson() {
        return paramsJson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cl_inst_task.FParamsJson
     *
     * @param paramsJson the value for cl_inst_task.FParamsJson
     *
     * @mbg.generated
     */
    public void setParamsJson(String paramsJson) {
        this.paramsJson = paramsJson == null ? null : paramsJson.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cl_inst_task.FFinishParamsJson
     *
     * @return the value of cl_inst_task.FFinishParamsJson
     *
     * @mbg.generated
     */
    public String getFinishParamsJson() {
        return finishParamsJson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cl_inst_task.FFinishParamsJson
     *
     * @param finishParamsJson the value for cl_inst_task.FFinishParamsJson
     *
     * @mbg.generated
     */
    public void setFinishParamsJson(String finishParamsJson) {
        this.finishParamsJson = finishParamsJson == null ? null : finishParamsJson.trim();
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
        TaskInstanceWithBLOBs other = (TaskInstanceWithBLOBs) that;
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
            && (this.getIsdelete() == null ? other.getIsdelete() == null : this.getIsdelete().equals(other.getIsdelete()))
            && (this.getParamsJson() == null ? other.getParamsJson() == null : this.getParamsJson().equals(other.getParamsJson()))
            && (this.getFinishParamsJson() == null ? other.getFinishParamsJson() == null : this.getFinishParamsJson().equals(other.getFinishParamsJson()));
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
        result = prime * result + ((getParamsJson() == null) ? 0 : getParamsJson().hashCode());
        result = prime * result + ((getFinishParamsJson() == null) ? 0 : getFinishParamsJson().hashCode());
        return result;
    }
}