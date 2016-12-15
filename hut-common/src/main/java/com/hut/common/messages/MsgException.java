package com.hut.common.messages;

/**
 * Created by Jared on 2016/12/15.
 */
public class MsgException extends RuntimeException {


    private int code;

    public MsgException(int code) {
        this.code = code;
    }

    public MsgException(int code,String message) {
        super(message);
        this.code = code;
    }

    public MsgException(int code,String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public MsgException(int code,Throwable cause) {
        super(cause);
        this.code = code;
    }

    public MsgException(int code,String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public int getCode() {
        return code;
    }


}
