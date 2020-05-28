package com.yimeiduo.business.entity;

import java.io.Serializable;

public class CommonResponse<T> implements Serializable {

    private int code;
    private String msg;
    private String token;
    private String systemTime;
    private int total;
    private T data;

//    {"token":null,"code":401,"msg":"手机号不存在","data":null,"total":0,"systemTime":"2019-12-23 08:59:31"}


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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(String systemTime) {
        this.systemTime = systemTime;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
