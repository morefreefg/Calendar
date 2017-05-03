package com.zsy.timeassistant.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.zsy.timeassistant.R;
import com.zsy.timeassistant.adapter.WeatherAdapter;
import com.zsy.timeassistant.bean.RequestImpl;
import com.zsy.timeassistant.bean.WeatherBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/*
 * 项目名:    Calenar
 * 创建时间:  2017/4/22 on 12:25
 * 描述:     TODO 天气预报
 */
public class WeatherActivity extends BaseActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initToolBar("天气预报-南京",true);
        listView = (ListView) findViewById(R.id.list);
        showDialog("正在加载天气数据...");
        createRetrofit().create(RequestImpl.class).getWeather("南京","0f024f4ce7994e468014ba8c0d5ecb29").enqueue(new Callback<WeatherBean>() {
            @Override
            public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                dimissDialog();
               WeatherBean weatherBean=response.body();
                if (weatherBean.getHeWeather5().size()>0){
                    WeatherAdapter adapter=new WeatherAdapter(WeatherActivity.this,weatherBean);
                    listView.setAdapter(adapter);
                }else {
                    Toast.makeText(WeatherActivity.this,"获取天气失败",Toast.LENGTH_SHORT);
                }

            }

            @Override
            public void onFailure(Call<WeatherBean> call, Throwable t) {
                dimissDialog();
                Toast.makeText(WeatherActivity.this,"网络错误",Toast.LENGTH_SHORT);
            }
        });
    }

        /**
         * 创建一个Retrofit请求对象
         *
         * @return Retrofit
         */
    public Retrofit createRetrofit() {
        return new Retrofit.Builder().baseUrl("https://free-api.heweather.com/v5/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
