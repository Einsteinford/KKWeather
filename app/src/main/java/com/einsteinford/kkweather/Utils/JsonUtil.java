package com.einsteinford.kkweather.Utils;


import android.content.Context;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by KK on 2016-08-22.
 */
public class JsonUtil {
    public static void parseJSONToWeather(String jsonData) {
        try {
            Log.i("JsonUtil", "parseJSONToWeather: start");
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather data service 3.0");
            JSONObject allJsonObject = jsonArray.getJSONObject(0);
            StringBuilder sb = new StringBuilder();

            String status = allJsonObject.getString("status");

            if (status.equals("ok")) {
                JSONObject aqi = allJsonObject.getJSONObject("aqi");
                JSONObject city = aqi.getJSONObject("city");
                city.getString("aqi");

                JSONObject basic = allJsonObject.getJSONObject("basic");
                JSONObject update = basic.getJSONObject("update");

                JSONArray daily_forecast = allJsonObject.getJSONArray("daily_forecast");

                JSONArray hourly_forecast = allJsonObject.getJSONArray("hourly_forecast");

                JSONObject now = allJsonObject.getJSONObject("now");

                JSONObject suggestion = allJsonObject.getJSONObject("suggestion");

                Log.i("JsonUtil", "parseJSONToWeather: " + suggestion.toString());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
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
                    CityListDatabaseUtil.insertCityList(id, city_chinese,city_english,region, province, latitude, longitude, context);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("CityList", ":InsertSuccess");
    }


    public static void parseJSONToAttractionsList(Context context, String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            String status = jsonObject.getString("status");
            if (status.equals("ok")) {
                JSONArray jsonArray = jsonObject.getJSONArray("city_info");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = (JSONObject) jsonArray.get(i);
                    //从数组的第0个开始取得
                    String city_chinese = object.getString("city");
                    String id = object.getString("id");
                    String latitude = object.getString("lat");
                    String longitude = object.getString("lon");
                    CityListDatabaseUtil.insertAttractionsList(id, city_chinese, latitude, longitude, context);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("AttractionsList", ":InsertSuccess");
    }
}
