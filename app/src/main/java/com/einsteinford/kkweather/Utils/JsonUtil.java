package com.einsteinford.kkweather.utils;


import android.content.ContentValues;
import android.content.Context;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by KK on 2016-08-22.
 */
public class JsonUtil {
    public static ContentValues parseJSONToWeather(String jsonData) {
        ContentValues contentValues = new ContentValues();
        try {
            Log.i("JsonUtil", "parseJSONToWeather: start");
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather data service 3.0");
            JSONObject allJsonObject = jsonArray.getJSONObject(0);
            String status = allJsonObject.getString("status");
            if (status.equals("ok")) {
                JSONObject basic = allJsonObject.getJSONObject("basic");
                contentValues.put("id", basic.getString("id"));
                contentValues.put("city", basic.getString("city"));
                JSONObject now = allJsonObject.getJSONObject("now");
                JSONObject now_cond = now.getJSONObject("cond");
                contentValues.put("now_cond_txt", now_cond.getString("txt"));
                contentValues.put("now_tmp", now.getString("tmp"));
                JSONArray daily_forecast = allJsonObject.getJSONArray("daily_forecast");
                for (int i = 0; i < 5; i++) {
                    String num = null;
                    switch (i){
                        case 0:
                            num = "first";
                            break;
                        case 1:
                            num = "second";
                            break;
                        case 2:
                            num = "third";
                            break;
                        case 3:
                            num = "fourth";
                            break;
                        case 4:
                            num = "fifth";
                            break;
                    }
                    JSONObject data = daily_forecast.getJSONObject(i);
                    contentValues.put(num + "_date", data.getString("date"));
                    JSONObject cond = data.getJSONObject("cond");
                    contentValues.put(num + "_txt_d", cond.getString("txt_d"));
                    contentValues.put(num + "_txt_n", cond.getString("txt_n"));
                    JSONObject tmp = data.getJSONObject("tmp");
                    contentValues.put(num + "_tmp_max", tmp.getString("max"));
                    contentValues.put(num + "_tmp_min", tmp.getString("min"));
                }
                Log.i("JsonUtil", "parseJSONToWeather: finish");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentValues;     //包含了所有的数据库信息
    }

    public static void parseJSONToCityList(Context context, String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            String status = jsonObject.getString("status");
            if (status.equals("ok")) {
                JSONArray jsonArray = jsonObject.getJSONArray("city_info");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = (JSONObject) jsonArray.get(i);
                    //从数组的第0个开始取得
                    String city_chinese = object.getString("city_chinese");
                    String city_english = object.getString("city_english");
                    String id = object.getString("id");
                    String region = object.getString("region");
                    String latitude = object.getString("latitude");
                    String longitude = object.getString("longitude");
                    String province = object.getString("province");
                    //直接执行数据库的添加操作
                    CityListDatabaseUtil.insertCityList(id, city_chinese, city_english, region, province, latitude, longitude, context);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("CityList", ":InsertSuccess");
    }


}
