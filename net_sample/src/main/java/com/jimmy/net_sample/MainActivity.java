package com.jimmy.net_sample;

import android.os.Bundle;
import android.view.View;


import com.jimmy.net.callback.CallBackProxy;
import com.jimmy.net.callback.DownloadProgressCallBack;
import com.jimmy.net.callback.SimpleCallBack;
import com.jimmy.net.exception.ApiException;
import com.jimmy.net.model.JsonRequestParam;
import com.jimmy.net.HttpClient;
import com.jimmy.net.subsciber.BaseSubscriber;
import com.jimmy.net.utils.HttpLog;
import com.jimmy.net_sample.databinding.HomeActivityBinding;
import com.jimmy.net_sample.model.UnstandardApiResult;
import com.jimmy.net_sample.model.Note;
import com.jimmy.net_sample.model.XMLApiResult;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private HomeActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    public void getJsonSync(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpLog.d("————————————————————————————————1");
                HttpClient.get("https://www.easy-mock.com/mock/5c515611b1c1b9153666e243/example/test/get/standard")
                        .syncRequest(true)//是否是同步请求，默认异步请求。true:同步请求
                        .execute(Object.class)
                        .subscribe(new BaseSubscriber<Object>() {
                            @Override
                            public void onError(final ApiException e) {
                                String a = "";
                                HttpLog.d("————————————————————————————————2");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        binding.tvResult.setText("error :" + e);
                                    }
                                });
                            }

                            @Override
                            public void onNext(final Object o) {
                                super.onNext(o);
                                HttpLog.d("————————————————————————————————3");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        HttpLog.d("————————————————————————————————4");
                                        binding.tvResult.setText("success :" + o.toString());

                                    }
                                });
                                HttpLog.d("————————————————————————————————5");
                            }
                        });
                HttpLog.d("————————————————————————————————6");
            }
        }).start();

    }

    public void getJsonAsyn(View view) {
        HttpClient.get("https://www.easy-mock.com/mock/5c515611b1c1b9153666e243/example/test/get/unstandard")
                .execute(new CallBackProxy<UnstandardApiResult<List>, List>(new SimpleCallBack<List>() {
                    @Override
                    public void onError(ApiException e) {
                        binding.tvResult.setText("error :" + e);
                        HttpLog.d("————————————————————————————————1");
                    }

                    @Override
                    public void onSuccess(List result) {
                        binding.tvResult.setText("success :" + result);
                        HttpLog.d("————————————————————————————————2");
                    }
                }) {
                });
        HttpLog.d("————————————————————————————————3");


    }

    public void postFormAsyn(View view) {
        HttpClient.post("https://www.easy-mock.com/mock/5c515611b1c1b9153666e243/example/post/login/test")
                .params("username", "admin")
                .params("password", "123")
                .execute(Object.class)
                .subscribe(new BaseSubscriber<Object>() {
                    @Override
                    public void onError(ApiException e) {
                        binding.tvResult.setText("error :" + e.getMessage());
                    }

                    @Override
                    public void onNext(Object o) {
                        super.onNext(o);
                        binding.tvResult.setText("success :" + o.toString());
                    }
                });
    }

    public void postJsonAsyn(View view) {
        HttpClient
                .post("https://www.easy-mock.com/mock/5c515611b1c1b9153666e243/example/post/login/test")
                .upJson(new JsonRequestParam("username", "admin"), new JsonRequestParam("password", "123"))
                .execute(Object.class)
                .subscribe(new BaseSubscriber<Object>() {
                    @Override
                    public void onError(ApiException e) {
                        binding.tvResult.setText("error :" + e.getMessage());
                    }

                    @Override
                    public void onNext(Object o) {
                        super.onNext(o);
                        binding.tvResult.setText("success :" + o.toString());
                    }
                });
    }

    public void getSyncXml(View view) {
        String xmlURL = "http://www.w3school.com.cn/xml/note.asp";
        HttpClient.get(xmlURL)
                .xmlRequest(true)
                .execute(new CallBackProxy<XMLApiResult<Note>, Note>(new SimpleCallBack<Note>() {
                    @Override
                    public void onError(ApiException e) {
                        binding.tvResult.setText("error :" + e);
                        HttpLog.d("————————————————————————————————x");
                    }

                    @Override
                    public void onSuccess(Note result) {
                        binding.tvResult.setText("success :" + result);
                        HttpLog.d("————————————————————————————————xy");
                    }
                }) {
                });
    }

    public void downloadZip(View view) {
        String url = "http://dl.oneth" +
                "ingpcs.com/download/minecloud/2.7.0/minecloud_2.7.0.1095-1-release.apk";
        Disposable disposable = HttpClient.downLoad(url)
                .savePath("/sdcard")
                .saveName("release_10000484.apk")//不设置默认名字是时间戳生成的
                .execute(new DownloadProgressCallBack<String>() {
                    @Override
                    public void update(long bytesRead, long contentLength, boolean done) {
                        int progress = (int) (bytesRead * 100 / contentLength);
                        HttpLog.e(progress + "% ");
                        binding.tvResult.setText(progress + "%");
                        if (done) {//下载完成
                        }
                    }

                    @Override
                    public void onStart() {
                        //开始下载
                        HttpLog.d("————————————————————————————————1");
                    }

                    @Override
                    public void onComplete(String path) {
                        //下载完成，path：下载文件保存的完整路径
                        HttpLog.d("————————————————————————————————2" + path);

                    }

                    @Override
                    public void onError(ApiException e) {
                        //下载失败
                        HttpLog.d("————————————————————————————————3" + e.getMessage());
                    }
                });
    }
}
