package com.einsteinford.kkweather.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.einsteinford.kkweather.SQLite.WeatherSQLiteHelper;

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
        //删光表
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("city_chinese", city_chinese);
        contentValues.put("city_english", city_english);
        contentValues.put("region", region);
        contentValues.put("province", province);
        contentValues.put("latitude", latitude);
        contentValues.put("longitude", longitude);
        db.insert("city_id", null, contentValues);

    }

    public static Map<String, String> queryCity(String city, Context context) {
        //模糊查询中文或拼音合适的城市，并返回Map的静态方法
        Map<String, String> cityMap = new HashMap<String, String>();
        SQLiteDatabase db = WeatherSQLiteHelper.getDatabaseInstance(context);
        Cursor cursor = db.query("city_id",
                new String[]{"id", "city_chinese", "region", "province"},
                "city_chinese like ? OR city_english like ?",
                new String[]{"%" + city + "%", "%" + city + "%"}, null, null, null);
        //模糊查询中文或拼音合适的城市
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex("id"));
                String city_chinese = cursor.getString(cursor.getColumnIndex("city_chinese"));
                String region = cursor.getString(cursor.getColumnIndex("region"));
                String province = cursor.getString(cursor.getColumnIndex("province"));
                cityMap.put(id,city_chinese+"-"+region+"-"+province);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return cityMap;
    }


    public static void insertAttractionsList(String id, String city_chinese, String latitude, String longitude, Context context) {
        //一个可用于添加用户数据的静态方法
        SQLiteDatabase db = WeatherSQLiteHelper.getDatabaseInstance(context);
        db.execSQL("drop table if exists attractions_id");
        //删光表
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("city_chinese", city_chinese);
        contentValues.put("latitude", latitude);
        contentValues.put("longitude", longitude);
        db.insert("attractions_id", null, contentValues);

    }

}
