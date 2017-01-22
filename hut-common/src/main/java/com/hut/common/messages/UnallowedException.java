package com.hut.common.messages;

/**
 * Created by Jared on 2017/1/20.
 */
public class UnallowedException extends RuntimeException {

    public UnallowedException() {
    }

    public UnallowedException(String message) {
        super(message);
    }

    public UnallowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnallowedException(Throwable cause) {
        super(cause);
    }

    public UnallowedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
