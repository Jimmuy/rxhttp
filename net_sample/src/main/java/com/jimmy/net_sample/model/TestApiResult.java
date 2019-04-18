package com.jimmy.net_sample.model;

import com.jimmy.net.model.ApiResult;

public class TestApiResult<T> extends ApiResult<T> {
    int error_code; //对应默认标准ApiResult的code
    String reason;//对应默认标准ApiResult的msg
    T result;//对应默认标准ApiResult的data
    @Override
    public T getData() {
        return result;
    }
    @Override
    public String getMsg() {
        return reason;
    }
    @Override
    public int getCode() {
        return error_code;
    }
    @Override
    public boolean isOk() {
        return error_code == 0;//表示成功
    }
}