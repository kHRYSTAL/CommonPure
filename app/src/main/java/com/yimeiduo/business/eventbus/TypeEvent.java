package com.yimeiduo.business.eventbus;

public class TypeEvent {

    private String type;
    public TypeEvent(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TypeEvent{" +
                "type='" + type + '\'' +
                '}';
    }
}
