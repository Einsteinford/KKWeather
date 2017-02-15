package com.einsteinford.kkweather.weatherprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.einsteinford.kkweather.sql.WeatherDbSchema.CityWeatherTable;
import com.einsteinford.kkweather.sql.WeatherSQLiteHelper;

/**
 * Created by KK on 2016-08-18.
 */
public class DatabaseProvider extends ContentProvider {


    private static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);   // Creates the root node of the URI tree.懒得翻译
        uriMatcher.addURI(UriList.AUTHORITY, CityWeatherTable.NAME, UriList.WEATHER_CHANGE);
    }

    public String mString;

    @Override
    public boolean onCreate() {
        WeatherSQLiteHelper.getDatabaseInstance(getContext());
        //实例化Helper
        return true;
    }


    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] strings, String s, String[] strings1, String s1) {
        SQLiteDatabase db = WeatherSQLiteHelper.getDatabaseInstance(getContext());
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case UriList.WEATHER_CHANGE:
                cursor = db.query(CityWeatherTable.NAME, strings, s, strings1, null, null, s1);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case UriList.WEATHER_CHANGE:
                return UriList.ITEM + "." + UriList.AUTHORITY + "." + CityWeatherTable.NAME;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        SQLiteDatabase db = WeatherSQLiteHelper.getDatabaseInstance(getContext());
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)) {
            case UriList.WEATHER_CHANGE:
                long newCity = db.insert(CityWeatherTable.NAME, null, contentValues);
                uriReturn = Uri.parse(UriList.CONTENT + UriList.AUTHORITY + "/" +
                        CityWeatherTable.NAME + "/" + newCity);
                break;
            default:
                break;
        }
        return uriReturn;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = WeatherSQLiteHelper.getDatabaseInstance(getContext());
        int deletedRows = 0;
        switch (uriMatcher.match(uri)) {
//            case WEATHER_CHANGE:
////                deletedRows = db.delete(CityWeatherTable.NAME, selection, selectionArgs);
//                break;
            default:
                break;
        }
        return deletedRows;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = WeatherSQLiteHelper.getDatabaseInstance(getContext());
        int updatedRows = 0;
        switch (uriMatcher.match(uri)) {
            case UriList.WEATHER_CHANGE:
                updatedRows = db.update(CityWeatherTable.NAME, values, selection, selectionArgs);
                break;
            default:
                break;
        }
        return updatedRows;
    }
}
