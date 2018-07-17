package com.adpanshi.cashloan.dispatch.userdata.exception;


import com.adpanshi.cashloan.data.common.exception.BusinessException;

/**
 * Created by zsw on 2017/9/1.
 *
 * @Description:
 */
public class DispatchUserDataException extends BusinessException {
    /**
     * 异常
     */
    public DispatchUserDataException() {
        super();
    }

    /**
     * @param message
     *            异常消息
     * @param cause
     *            异常原因
     */
    public DispatchUserDataException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     *            异常消息
     */
    public DispatchUserDataException(String message) {
        super(message);
    }

    /**
     * @param message
     *            异常消息
     */
    public DispatchUserDataException(Integer message) {
        super(String.valueOf(message));
    }

    /**
     * @param cause
     *            异常原因
     */
    public DispatchUserDataException(Throwable cause) {
        super(cause);
    }
}
