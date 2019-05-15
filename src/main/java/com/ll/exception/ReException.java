package com.ll.exception;

public class ReException extends RuntimeException {

    private static final long serialVersionUID = 6208270796383836348L;

    private String msg;
    private int code = 500;

    public ReException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ReException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public ReException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
