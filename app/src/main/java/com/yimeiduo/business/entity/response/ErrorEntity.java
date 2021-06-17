package com.yimeiduo.business.entity.response;

import com.yimeiduo.business.base.BaseData;

import java.io.IOException;

/**
 * 登录失败,仅登陆失败出现
 */
public class ErrorEntity extends BaseData {

    /**
     * error : 400
     * code : -2
     * message : 找不到该用户
     */
    //{"token":null,"code":401,"msg":"手机号不存在","data":null,"total":0,"systemTime":"2019-12-23 08:59:31"}
    private int code;
    private String msg;
    private String systemTime;
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public String getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(String systemTime) {
        this.systemTime = systemTime;
    }

    @Override
    public String toString() {
        return "ErrorEntity{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", systemTime='" + systemTime + '\'' +
                '}';
    }
}
