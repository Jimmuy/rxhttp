package com.jimmy.net_sample;

import android.app.Application;

import com.jimmy.net.HttpClient;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        HttpClient.init(this);
        HttpClient.getInstance()
                // 打开该调试开关并设置TAG,不需要就不要加入该行
                // 最后的true表示是否打印内部异常，一般打开方便调试错误
                .debug("HttpClient____", true);
    }
}
