package com.einsteinford.kkweather.http;

import com.einsteinford.kkweather.bean.WeatherBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by KK on 2017/1/11.
 */

public interface WeatherApi {
    @GET("forecast")
        //请求方式为GET 接口地址为forecast
    Call<WeatherBean> getWeatherInfo(
            @Query(HttpConstans.CITY) String city, @Query(HttpConstans.KEY) String key);
    //getWeatherInfo()是有参方法，接收String类型的参数,@Query表示查询函数

    @GET("forecast")
        //请求方式为GET 接口地址为forecast
    Observable<WeatherBean> getCityWeather(
            @Query(HttpConstans.CITY) String city, @Query(HttpConstans.KEY) String key);
    //getWeatherInfo()是有参方法，接收String类型的参数,@Query表示查询函数
}
