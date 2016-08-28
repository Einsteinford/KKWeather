package com.einsteinford.kkweather.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.einsteinford.kkweather.R;
import com.einsteinford.kkweather.sql.WeatherDbSchema.CityWeatherTable.Cols;
import com.einsteinford.kkweather.utils.CityListDatabaseUtil;

/**
 * Created by KK on 2016-08-23.
 */
public class WeatherFragment extends android.support.v4.app.Fragment {
    String TAG = getClass().getSimpleName();
    private TextView mTvWeatherNow;
    private TextView mTvCity;
    private TextView mTvTemperature;
    private TextView firstTvDate;
    private TextView firstTvCond;
    private TextView firstTvTmp;
    private TextView secondTvDate;
    private TextView secondTvCond;
    private TextView secondTvTmp;
    private TextView thirdTvDate;
    private TextView thirdTvCond;
    private TextView thirdTvTmp;
    private TextView fourthTvDate;
    private TextView fourthTvCond;
    private TextView fourthTvTmp;
    private TextView fifthTvDate;
    private TextView fifthTvCond;
    private TextView fifthTvTmp;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.weather_fragment, container, false);
        String ID = getArguments().getString("ID");
        mTvWeatherNow = (TextView) view.findViewById(R.id.tv_weather_now);
        mTvCity = (TextView) view.findViewById(R.id.tv_city);
        mTvTemperature = (TextView) view.findViewById(R.id.tv_temperature);
        firstTvDate = (TextView) view.findViewById(R.id.first_tv_date);
        firstTvCond = (TextView) view.findViewById(R.id.first_tv_cond);
        firstTvTmp = (TextView) view.findViewById(R.id.first_tv_tmp);
        secondTvDate = (TextView) view.findViewById(R.id.second_tv_date);
        secondTvCond = (TextView) view.findViewById(R.id.second_tv_cond);
        secondTvTmp = (TextView) view.findViewById(R.id.second_tv_tmp);
        thirdTvDate = (TextView) view.findViewById(R.id.third_tv_date);
        thirdTvCond = (TextView) view.findViewById(R.id.third_tv_cond);
        thirdTvTmp = (TextView) view.findViewById(R.id.third_tv_tmp);
        fourthTvDate = (TextView) view.findViewById(R.id.fourth_tv_date);
        fourthTvCond = (TextView) view.findViewById(R.id.fourth_tv_cond);
        fourthTvTmp = (TextView) view.findViewById(R.id.fourth_tv_tmp);
        fifthTvDate = (TextView) view.findViewById(R.id.fifth_tv_date);
        fifthTvCond = (TextView) view.findViewById(R.id.fifth_tv_cond);
        fifthTvTmp = (TextView) view.findViewById(R.id.fifth_tv_tmp);
//        mTvWeather = (TextView) view.findViewById(R.id.tv_weather);

        Log.i(TAG, "onCreateView: +FRAGMENT!!" + ID);
        //必须在完成数据库存入后才进行这些
        mTvWeatherNow.setText(CityListDatabaseUtil.queryWeather(ID, Cols.NOW_COND_TXT, getActivity()));
        mTvCity.setText(CityListDatabaseUtil.queryWeather(ID, Cols.CITY, getActivity()));
        StringBuilder temp = new StringBuilder();
        temp.append(CityListDatabaseUtil.queryWeather(ID, Cols.NOW_TMP, getActivity())).append("℃");
        mTvTemperature.setText(temp);
        temp.delete(0, temp.length());
        //------------------------------------
        temp.append(CityListDatabaseUtil.queryWeather(ID, Cols.FIRST_DATE, getActivity()));
        firstTvDate.setText(deleteStr(temp.toString()));
        temp.delete(0, temp.length());
        firstTvCond.setText(CityListDatabaseUtil.queryWeather(ID, Cols.FIRST_TXT_D, getActivity()));

        temp.append(CityListDatabaseUtil.queryWeather(ID, Cols.FIRST_TMP_MAX, getActivity()))
                .append("°/")
                .append(CityListDatabaseUtil.queryWeather(ID, Cols.FIRST_TMP_MIN, getActivity()))
                .append("°");
        firstTvTmp.setText(temp);
        temp.delete(0, temp.length());
        //------------------------------------
        temp.append(CityListDatabaseUtil.queryWeather(ID, Cols.SECOND_DATE, getActivity()));
        secondTvDate.setText(deleteStr(temp.toString()));
        temp.delete(0, temp.length());
        secondTvCond.setText(CityListDatabaseUtil.queryWeather(ID, Cols.SECOND_TXT_D, getActivity()));

        temp.append(CityListDatabaseUtil.queryWeather(ID, Cols.SECOND_TMP_MAX, getActivity()))
                .append("°/")
                .append(CityListDatabaseUtil.queryWeather(ID, Cols.SECOND_TMP_MIN, getActivity()))
                .append("°");
        secondTvTmp.setText(temp);
        temp.delete(0, temp.length());
        //------------------------------------
        temp.append(CityListDatabaseUtil.queryWeather(ID, Cols.THIRD_DATE, getActivity()));
        thirdTvDate.setText(deleteStr(temp.toString()));
        temp.delete(0, temp.length());
        thirdTvCond.setText(CityListDatabaseUtil.queryWeather(ID, Cols.THIRD_TXT_D, getActivity()));

        temp.append(CityListDatabaseUtil.queryWeather(ID, Cols.THIRD_TMP_MAX, getActivity()))
                .append("°/")
                .append(CityListDatabaseUtil.queryWeather(ID, Cols.THIRD_TMP_MIN, getActivity()))
                .append("°");
        thirdTvTmp.setText(temp);
        temp.delete(0, temp.length());
        //------------------------------------
        temp.append(CityListDatabaseUtil.queryWeather(ID, Cols.FOURTH_DATE, getActivity()));
        fourthTvDate.setText(deleteStr(temp.toString()));
        temp.delete(0, temp.length());
        fourthTvCond.setText(CityListDatabaseUtil.queryWeather(ID, Cols.FOURTH_TXT_D, getActivity()));

        temp.append(CityListDatabaseUtil.queryWeather(ID, Cols.FOURTH_TMP_MAX, getActivity()))
                .append("°/")
                .append(CityListDatabaseUtil.queryWeather(ID, Cols.FOURTH_TMP_MIN, getActivity()))
                .append("°");
        fourthTvTmp.setText(temp);
        temp.delete(0, temp.length());
        //------------------------------------
        temp.append(CityListDatabaseUtil.queryWeather(ID, Cols.FIFTH_DATE, getActivity()));
        fifthTvDate.setText(deleteStr(temp.toString()));
        temp.delete(0, temp.length());
        fifthTvCond.setText(CityListDatabaseUtil.queryWeather(ID, Cols.FIFTH_TXT_D, getActivity()));

        temp.append(CityListDatabaseUtil.queryWeather(ID, Cols.FIFTH_TMP_MAX, getActivity()))
                .append("°/")
                .append(CityListDatabaseUtil.queryWeather(ID, Cols.FIFTH_TMP_MIN, getActivity()))
                .append("°");
        fifthTvTmp.setText(temp);
        temp.delete(0, temp.length());
        //------------------------------------


        return view;
    }

    public String deleteStr(String name) {  //用来统一格式化城市名，一般来说自己选的城市不太会有两个相同的地名
        String str[] = name.split("-");
        return str[1] + "月" + str[2] + "日";
    }
}
