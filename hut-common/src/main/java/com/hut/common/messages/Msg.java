package com.hut.common.messages;

/**
 * Created by Jared on 2016/12/11.
 */
public class Msg<T> {

    /**操作成功*/
    public static final int  OK = 0;

    /**未知异常*/
    public static final int  UNKNOW = -1;

    /**参数不合法*/
    public static final int  PARAMERROR = -2;

    /**权限不足*/
    public static final int  UNAUTH = 1001;

    /**文件大小超出限制*/
    public static final int FILESIZE_LIMITED=2001;

    /**文件上传成功*/
    public static final int FILEUPLOADSUCCESSF = 2000;

    /**文件上传失败*/
    public static final int FILEUPLOADFAIl = 2002;

    private int code;
    private String message;
    private T entity;

    public Msg(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Msg(int code, String message, T entity) {
        this.code = code;
        this.message = message;
        this.entity = entity;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }


    public static Msg build(int code, String message){
        return new Msg(code,message);
    }

}
