package com.weight;

/**
 * EventBus 公共；类
 */
public class UiEvent {
    private Object mObject;
    private String key;

    public Object getObject() {
        return mObject;
    }

    public void setObject(Object object) {
        mObject = object;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
