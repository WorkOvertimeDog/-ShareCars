package com.greenfinch.sharecars.utils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * Created by pangjiaqi on 2017/9/5.
 */

public class HttpUtils {

    private static HttpUtils mHttpUtils;

    public static synchronized HttpUtils getInstance() {
        synchronized (HttpUtils.class) {
            if (mHttpUtils == null) {
                mHttpUtils = new HttpUtils();
            }
        }
        return mHttpUtils;
    }

    /**
     * 无参数的get请求
     */

    public void okGet(String url) {
        OkGo.<String>get(url)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }

    /**
     * 无参数post请求
     *
     * @param url
     */
    public void okPost(String url) {
        OkGo.<String>post(url)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }

}
