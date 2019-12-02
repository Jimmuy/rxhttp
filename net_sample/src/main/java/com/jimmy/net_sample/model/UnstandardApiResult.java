package com.jimmy.net_sample.model;

import com.jimmy.net.model.ApiResult;

public class UnstandardApiResult<T> extends ApiResult<T> {
     T data2;
    @Override
    public T getBaseData() {
        return data2;
    }
    @Override
    public boolean isOk() {
        return data2 != null;//表示成功
    }
    @Override
    public String getMsg() {
        return "xxx";
    }
    @Override
    public int getCode() {
        return 200;
    }
}
