package com.adpanshi.cashloan.dispatch.config.bo;

import java.io.Serializable;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public class TaskConfigBo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String number;
    private String name;
    private String adapterCode;//适配器编码
    private String desc;
    private Integer nodeId;
    private Boolean isSync;
    private Boolean isPause;
    private Boolean isFinalTask;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdapterCode() {
        return adapterCode;
    }

    public void setAdapterCode(String adapterCode) {
        this.adapterCode = adapterCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Boolean getIsSync() {
        return isSync;
    }

    public void setIsSync(Boolean sync) {
        isSync = sync;
    }

    public Boolean getIsPause() {
        return isPause;
    }

    public void setIsPause(Boolean pause) {
        isPause = pause;
    }

    public Boolean getIsFinalTask() {
        return isFinalTask;
    }

    public void setIsFinalTask(Boolean finalTask) {
        isFinalTask = finalTask;
    }
}
