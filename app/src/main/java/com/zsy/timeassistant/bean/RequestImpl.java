package com.zsy.timeassistant.bean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/*
 * 项目名:    Calendar
 * 描述:     存放Retrofit请求网络所用到的接口和服务器请求数据接口
 */
public interface RequestImpl {


    @POST("forecast")
    Call<WeatherBean> getWeather(@Query("city") String city,@Query("key") String key);
}


