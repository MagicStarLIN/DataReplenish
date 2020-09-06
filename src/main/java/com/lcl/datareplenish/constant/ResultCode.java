package com.lcl.datareplenish.constant;

import java.io.Serializable;

public class ResultCode implements Serializable {

    private static final long serialVersionUID = 8460819490759366955L;

    public static final ResultCode SUCC = new ResultCode(0, "Success");

    public static final ResultCode FAIL = new ResultCode(1, "系统服务错误");


    private int code;

    private String msg;

    public ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResultCode{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
