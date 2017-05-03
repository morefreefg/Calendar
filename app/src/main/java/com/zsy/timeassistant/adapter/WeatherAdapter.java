package com.zsy.timeassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zsy.timeassistant.R;
import com.zsy.timeassistant.bean.WeatherBean;

public class WeatherAdapter extends BaseAdapter {
    private Context context;
    private WeatherBean weatherBean;

    public WeatherAdapter(Context context, WeatherBean weatherBean) {
        this.context = context;
        this.weatherBean = weatherBean;
    }

    @Override
    public int getCount() {
        return weatherBean.getHeWeather5().get(0).getDaily_forecast().size();
    }

    @Override
    public Object getItem(int position) {
        return weatherBean.getHeWeather5().get(0).getDaily_forecast().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_weather, null);
        TextView tvDayOdWeek = (TextView) view.findViewById(R.id.tvDayofWeek);
        TextView tvTemperature = (TextView) view.findViewById(R.id.tvTemperature);
        TextView tvWeather = (TextView) view.findViewById(R.id.tvWeather);
        WeatherBean.HeWeather5Bean.DailyForecastBean dailyForecastBean = weatherBean.getHeWeather5().get(0).getDaily_forecast().get(position);

        tvDayOdWeek.setText("日期：" + dailyForecastBean.getDate());
        tvTemperature.setText("温度：" + dailyForecastBean.getTmp().getMin() + "℃~" + dailyForecastBean.getTmp().getMax() + "℃");
        tvWeather.setText("天气：" + dailyForecastBean.getCond().getTxt_d());
        return view;
    }

}
