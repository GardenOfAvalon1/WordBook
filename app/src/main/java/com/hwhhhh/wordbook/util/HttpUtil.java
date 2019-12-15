package com.hwhhhh.wordbook.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {//发送https请求
    public static void setHttpRequest(final String address, final HttpCallBackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    connection = (HttpURLConnection) new URL(address).openConnection();
                    connection.setRequestMethod("GET");//设置请求方法
                    connection.setReadTimeout(8000);//设置连接超时时间（毫秒）
                    connection.setConnectTimeout(8000);//设置读取超时时间（毫秒）
                    InputStream inputStream = connection.getInputStream();//返回输入流
                    if (listener != null) {
                        listener.onFinish(inputStream);
                    }
                } catch (Exception e) {
                    if (listener != null) {
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
