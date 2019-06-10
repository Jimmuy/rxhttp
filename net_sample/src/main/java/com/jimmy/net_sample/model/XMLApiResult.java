package com.jimmy.net_sample.model;

import com.jimmy.net.model.ApiResult;

public class XMLApiResult<T> extends ApiResult<T> {

    T result;//对应默认标准ApiResult的data

    @Override
    public T getBaseData() {
        return result;
    }

    @Override
    public String getMsg() {
        return "xxx";
    }

    @Override
    public int getCode() {
        return 200;
    }

    @Override
    public boolean isOk() {
        return true;//表示成功
    }
}