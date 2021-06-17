package com.yimeiduo.business.entity.response;

import com.yimeiduo.business.base.BaseData;

/**
 * 请求异常
 */

public class ExceptionEntity extends BaseData {
    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", data=" + data +
                ", success=" + success +
                '}';
    }

    /**
     * code : -3
     * message : JSON parse error: Cannot deserialize value of type `cn.ricelink.yxos.provider.model.client.enums.ClientStatus` from String "FOLOWING": value not one of declared Enum instance names: [GIVE_UP, SIGNED, FOLLOWING]; nested exception is com.fasterxml.jackson.databind.exc.InvalidFormatException: Cannot deserialize value of type `cn.ricelink.yxos.provider.model.client.enums.ClientStatus` from String "FOLOWING": value not one of declared Enum instance names: [GIVE_UP, SIGNED, FOLLOWING]
     * at [Source: (PushbackInputStream); line: 1, column: 17] (through reference chain: cn.ricelink.yxos.provider.pojo.elder.request.ClientVisitInfoRequest["clientStatus"])
     * timestamp : null
     * data : null
     * success : false
     */

    private int code;
    private String message;
    private Object timestamp;
    private Object data;
    private boolean success;

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

    public Object getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Object timestamp) {
        this.timestamp = timestamp;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
