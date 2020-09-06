package com.lcl.datareplenish.constant;


import java.io.Serializable;
import java.util.Collections;

/**
 * @author liuchanglin
 * @created 2020-09-06 00:36
 * copy from zp-chat
 */
@SuppressWarnings("unchecked")
public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = -3540272309116909111L;

    /**
     * 错误码，0代表成功，其他代表失败
     */
    protected int code;

    /**
     * 错误信息，对应错误码
     */
    protected String message;

    /**
     * 业务数据
     */
    protected T data = (T) Collections.EMPTY_MAP;

    public ApiResult() {
    }

    public ApiResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 失败的默认对象
     */
    public static final ApiResult FAIL = ApiResult.failed(ResultCode.FAIL);


    private static <T> ApiResult<T> wrap(int code, String msg, T data) {
        return new ApiResult<T>(code, msg, data);
    }

    public static <T> ApiResult<T> success(T data) {
        return wrap(ResultCode.SUCC.getCode(), ResultCode.SUCC.getMsg(), data);
    }

    public static <T> ApiResult<T> failed(int code, String msg) {
        return wrap(code, msg, (T) Collections.EMPTY_MAP);
    }

    public static <T> ApiResult<T> failed(String msg) {
        return failed(ResultCode.FAIL.getCode(), msg);
    }

    public static <T> ApiResult<T> failed(ResultCode resultCode) {
        return failed(resultCode.getCode(), resultCode.getMsg());
    }

    public static <T> ApiResult<T> failed(ResultCode resultCode, String message) {
        return failed(resultCode.getCode(), message);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

}
