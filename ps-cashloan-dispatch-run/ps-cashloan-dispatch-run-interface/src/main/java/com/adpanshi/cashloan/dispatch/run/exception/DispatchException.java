package com.adpanshi.cashloan.dispatch.run.exception;

/**
 * Created by zsw on 2018/7/12 0012.
 */
public class DispatchException extends RuntimeException {
    public DispatchException(){}

    public DispatchException(String message){
        super(message);
    }

    public DispatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public DispatchException(Throwable cause) {
        super(cause);
    }
}
