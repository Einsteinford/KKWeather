package com.einsteinford.kkweather.utils;

import android.util.Log;

import com.einsteinford.kkweather.bean.WeatherBean;
import com.einsteinford.kkweather.http.HttpConstans;
import com.einsteinford.kkweather.http.WeatherApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by KK on 2016-08-22.
 */
public class HttpUtils {

    public static void sendHttpRequest(final String address, final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);

                    int responseCode = connection.getResponseCode();
                    Log.d("HttpUtils", "run: " + responseCode);

                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line).append("\r\n");
                    }
                    reader.close();
                    if (listener != null) {
                        //回调onFinish方法
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //回调onError方法
                    listener.onError(e);
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();

    }

    public static void sendRequestWithLocalJson(final InputStream in, final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line).append("\r\n");
                    }
                    reader.close();
                    if (listener != null) {
                        //回调onFinish方法
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //回调onError方法
                    listener.onError(e);
                }
            }
        }).start();
    }

    public interface HttpCallbackListener {
        void onFinish(String response);

        void onError(Exception e);
    }


    private static HttpUtils sHttpUtils;    //私有化构造函数

    public static HttpUtils getInstance() {   //创建静态单例对象
        if (sHttpUtils == null) {
            sHttpUtils = new HttpUtils();
        }
        return sHttpUtils;
    }

    public void test() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
    }

    /*
    city: string required
    城市名称 city可通过城市中英文名称、ID和IP地址进行，例如city=北京，city=beijing，city=CN101010100，city= 60.194.130.1
    key: string required
    用户认证key
    lang: string  多语言，默认为中文，可选参数
    */
    public Call<WeatherBean> sendRequest(String city) {
        Retrofit retrofit = new Retrofit.Builder()  //创建Retrofit对象
                .baseUrl(HttpConstans.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApi api = retrofit.create(WeatherApi.class);     //TODO 通过retrofit创建api对象
        return api.getWeatherInfo(city, HttpConstans.MY_KEY);
    }

    public Observable<WeatherBean> sendRetrofitRequest(String city) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        Retrofit retrofit = new Retrofit.Builder()  //创建Retrofit对象
                .baseUrl(HttpConstans.HOST)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        WeatherApi api = retrofit.create(WeatherApi.class);     //TODO 通过retrofit创建api对象
        return api.getCityWeather(city, HttpConstans.MY_KEY);
    }
}
