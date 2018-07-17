package com.adpanshi.cashloan.dispatch.config.exception;

/**
 * Created by zsw on 2018/7/13 0013.
 */
public class CreditDispatchException extends RuntimeException {
    public CreditDispatchException(){}

    public CreditDispatchException(String message){
        super(message);
    }

    public CreditDispatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreditDispatchException(Throwable cause) {
        super(cause);
    }
}
