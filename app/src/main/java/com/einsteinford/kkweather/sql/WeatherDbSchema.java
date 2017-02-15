package com.einsteinford.kkweather.sql;


public class WeatherDbSchema {
    public static final String DBNAME = "Weather.db";

    public static final class CityIdTable {
        public static final String NAME = "city_id";

        public static final class Cols {
            public static final String ID = "id";
            public static final String CTCN = "city_chinese";
            public static final String CTEN = "city_english";
            public static final String REG = "region";
            public static final String PROV = "province";
            public static final String LAT = "latitude";
            public static final String LON = "longitude";
        }
    }

    public static final class CityWeatherTable {
        public static final String NAME = "city_weather";

        public static final class Cols {
            public static final String ID = "id";
            public static final String CITY = "city";
            public static final String NOW_COND_TXT = "now_cond_txt";
            public static final String NOW_TMP = "now_tmp";
            public static final String FIRST_DATE = "first_date";
            public static final String FIRST_TXT_D = "first_txt_d";
            public static final String FIRST_TXT_N = "first_txt_n";
            public static final String FIRST_TMP_MAX = "first_tmp_max";
            public static final String FIRST_TMP_MIN = "first_tmp_min";
            public static final String SECOND_DATE = "second_date";
            public static final String SECOND_TXT_D = "second_txt_d";
            public static final String SECOND_TXT_N = "second_txt_n";
            public static final String SECOND_TMP_MAX = "second_tmp_max";
            public static final String SECOND_TMP_MIN = "second_tmp_min";
            public static final String THIRD_DATE = "third_date";
            public static final String THIRD_TXT_D = "third_txt_d";
            public static final String THIRD_TXT_N = "third_txt_n";
            public static final String THIRD_TMP_MAX = "third_tmp_max";
            public static final String THIRD_TMP_MIN = "third_tmp_min";
            public static final String FOURTH_DATE = "fourth_date";
            public static final String FOURTH_TXT_D = "fourth_txt_d";
            public static final String FOURTH_TXT_N = "fourth_txt_n";
            public static final String FOURTH_TMP_MAX = "fourth_tmp_max";
            public static final String FOURTH_TMP_MIN = "fourth_tmp_min";
            public static final String FIFTH_TMP_MIN = "fifth_tmp_min";
            public static final String FIFTH_TMP_MAX = "fifth_tmp_max";
            public static final String FIFTH_DATE = "fifth_date";
            public static final String FIFTH_TXT_D = "fifth_txt_d";
            public static final String FIFTH_TXT_N = "fifth_txt_n";
        }
    }
}
