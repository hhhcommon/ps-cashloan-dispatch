package com.adpanshi.cashloan.dispatch.run.bo;

import com.adpanshi.cashloan.dispatch.run.enums.StatusCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * 运行响应
 */
public class DispatchRunResponseBo<T> implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private int ret_code;

    private String ret_msg;

    private long time = System.currentTimeMillis();

    private T data;

    public DispatchRunResponseBo() {
    }

    public DispatchRunResponseBo(StatusCode code) {
        this.ret_code = code.getValue();
        this.ret_msg = code.getContent();
    }

    public DispatchRunResponseBo(StatusCode code, T data) {
        this.ret_code = code.getValue();
        this.ret_msg = code.getContent();
        this.data = data;
    }

    public DispatchRunResponseBo(StatusCode code, String remark, T data) {
        this.ret_code = code.getValue();
        this.ret_msg = StringUtils.isEmpty(remark)?code.getContent():remark;
        this.data = data;
    }

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public String getRet_msg() {
        return ret_msg;
    }

    public void setRet_msg(String ret_msg) {
        this.ret_msg = ret_msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <R> DispatchRunResponseBo<R> success() {
        return new DispatchRunResponseBo<R>(StatusCode.SUCCESS);
    }

    public static <R> DispatchRunResponseBo<R> success(R data) {
        return new DispatchRunResponseBo<R>(StatusCode.SUCCESS, data);
    }

    public static <R> DispatchRunResponseBo<R> success(R data, String remark) {
        return new DispatchRunResponseBo<R>(StatusCode.SUCCESS, remark, data);
    }

    public static <R> DispatchRunResponseBo<R> error(StatusCode code) {
        return new DispatchRunResponseBo<R>(code, null);
    }

    public static <R> DispatchRunResponseBo<R> error(StatusCode code, String remark) {
        return new DispatchRunResponseBo<R>(code, remark, null);
    }

    @JsonIgnore
    private Integer nodeInstId;

    public Integer getNodeInstId() {
        return nodeInstId;
    }

    public void setNodeInstId(Integer nodeInstId) {
        this.nodeInstId = nodeInstId;
    }

}
