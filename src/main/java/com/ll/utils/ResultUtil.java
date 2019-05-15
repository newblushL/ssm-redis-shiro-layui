package com.ll.utils;

import java.io.Serializable;

public class ResultUtil implements Serializable {

    private static final long serialVersionUID = -8523169129956579360L;

    private Integer code;
    private String msg = "";
    private Long count = 0L;
    private Object data;

    public ResultUtil() {
        super();
    }

    public ResultUtil(Integer code) {
        super();
        this.code = code;
    }

    public ResultUtil(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public static ResultUtil ok() {
        return new ResultUtil(1);
    }

    public static ResultUtil ok(Object list) {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(1);
        resultUtil.setData(list);
        return resultUtil;
    }

    public static ResultUtil ok(String msg) {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(1);
        resultUtil.setMsg(msg);
        return resultUtil;
    }

    public static ResultUtil error() {
        return new ResultUtil(500, "没有此权限，请联系超管！");
    }

    public static ResultUtil error(String msg) {
        return new ResultUtil(500, msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
