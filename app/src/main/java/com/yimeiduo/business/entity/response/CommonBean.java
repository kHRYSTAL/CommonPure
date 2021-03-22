package com.yimeiduo.business.entity.response;

import java.io.Serializable;

public class CommonBean implements Serializable {

    private String name;
    private int state;

    public CommonBean(String name, int state){
        this.name = name;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


}
