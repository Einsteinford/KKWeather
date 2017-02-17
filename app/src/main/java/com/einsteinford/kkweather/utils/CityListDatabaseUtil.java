package com.einsteinford.kkweather.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.einsteinford.kkweather.sql.WeatherDbSchema.CityIdTable;
import com.einsteinford.kkweather.sql.WeatherDbSchema.CityWeatherTable;
import com.einsteinford.kkweather.sql.WeatherSQLiteHelper;
import com.einsteinford.kkweather.weatherprovider.UriList;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by KK on 2016-08-16.
 */
public class CityListDatabaseUtil {
    //稍后可以修改为contextProvider的使用方式

    public static void insertCityList(String id, String city_chinese, String city_english, String region, String province, String latitude, String longitude, Context context) {
        //一个可用于添加用户数据的静态方法
        SQLiteDatabase db = WeatherSQLiteHelper.getDatabaseInstance(context);

        ContentValues contentValues = new ContentValues();
        contentValues.put(CityIdTable.Cols.ID, id);
        contentValues.put(CityIdTable.Cols.CTCN, city_chinese);
        contentValues.put(CityIdTable.Cols.CTEN, city_english);
        contentValues.put(CityIdTable.Cols.REG, region);
        contentValues.put(CityIdTable.Cols.PROV, province);
        contentValues.put(CityIdTable.Cols.LAT, latitude);
        contentValues.put(CityIdTable.Cols.LON, longitude);
        db.insert(CityIdTable.NAME, null, contentValues);
    }

    public static Map<String, String> queryCity(String city, Context context) {
        //模糊查询中文或拼音合适的城市，并返回Map的静态方法
        Map<String, String> cityMap = new HashMap<String, String>();
        SQLiteDatabase db = WeatherSQLiteHelper.getDatabaseInstance(context);
        Cursor cursor = db.query(CityIdTable.NAME,
                new String[]{CityIdTable.Cols.ID, CityIdTable.Cols.CTCN, CityIdTable.Cols.REG, CityIdTable.Cols.PROV},
                CityIdTable.Cols.CTCN + " like ? OR " + CityIdTable.Cols.CTEN + " like ?",
                new String[]{"%" + city + "%", "%" + city + "%"}, null, null, null);
        //模糊查询中文或拼音合适的城市
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(CityIdTable.Cols.ID));
                String city_chinese = cursor.getString(cursor.getColumnIndex(CityIdTable.Cols.CTCN));
                String region = cursor.getString(cursor.getColumnIndex(CityIdTable.Cols.REG));
                String province = cursor.getString(cursor.getColumnIndex(CityIdTable.Cols.PROV));
                cityMap.put(id, city_chinese + "-" + region + "-" + province);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return cityMap;
    }
//--------------------以下是关于天气数据的方法
    public static void insertCityWeather(final String id, final ContentValues values, String name,final Context context, final SQLCallbackListener listener) {
        //一个可用于添加用户数据的静态方法
        Log.i("CityListDatabaseUtil", "insertCityWeather: 插入天气开始");
        try {
            Cursor cursor =context.getContentResolver().query(Uri.parse(UriList.CONTENT+UriList.AUTHORITY+"/"+CityWeatherTable.NAME),
                    null, "id = ?", new String[]{id}, null);
            if (cursor != null && !cursor.moveToFirst()) {        //cursor为空，就是说URI错了。而如果没有此id的数据，则插入
                context.getContentResolver().insert(Uri.parse(UriList.CONTENT + UriList.AUTHORITY + "/" + CityWeatherTable.NAME), values);
                cursor.close();
                Log.i("CityListDatabaseUtil", "insertCityWeather: 插入天气成功");
            }
            if (listener != null) {
                listener.onFinish(name);
            }
        } catch (Exception e) {
            if (listener != null) {
                listener.onError(e);
            }
        }

    }
    public static void updateCityWeather(final String id, final ContentValues values,final Context context, final SQLCallbackListener listener) {
        //一个可用于添加用户数据的静态方法
        Log.i("CityListDatabaseUtil", "updateCityWeather: 更新天气开始"+id);
        try {
            context.getContentResolver().update(Uri.parse(UriList.CONTENT+UriList.AUTHORITY+"/"+CityWeatherTable.NAME),
                    values,CityWeatherTable.Cols.ID + " = ?",new String[]{id});
                Log.i("CityListDatabaseUtil", "updateCityWeather: 更新天气成功"+id);
            if (listener != null) {
                listener.onFinish("");
            }
        } catch (Exception e) {
            if (listener != null) {
                listener.onError(e);
            }
        }

    }

    public static String queryWeather(String ID, String request, Context context) {
        //查询城市天气属性，并返回String的静态方法
        String respond = "";        //没查询到对应的属性，就返回空白的，免得空指针
        Cursor cursor =context.getContentResolver().query(Uri.parse(UriList.CONTENT+UriList.AUTHORITY+"/"+CityWeatherTable.NAME),
                new String[]{request}, CityWeatherTable.Cols.ID + " = ?", new String[]{ID}, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                respond = cursor.getString(cursor.getColumnIndex(request));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return respond;
    }

    public interface SQLCallbackListener {
        void onFinish(String response);

        void onError(Exception e);
    }
}
