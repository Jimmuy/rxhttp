package com.jimmy.net.model;

public class JsonRequestParam {

    public JsonRequestParam(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    private String key;


    private Object value;


    public String getKey() {
        return key;
    }


    public Object getValue() {
        return value;
    }

}
