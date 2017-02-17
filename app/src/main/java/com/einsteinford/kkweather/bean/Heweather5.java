package com.einsteinford.kkweather.bean;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Heweather5 {

    private Basic basic;
    @SerializedName("daily_forecast")
    private List<DailyForecast> dailyForecast;
    private String status;
    public void setBasic(Basic basic) {
         this.basic = basic;
     }
     public Basic getBasic() {
         return basic;
     }

    public void setDailyForecast(List<DailyForecast> dailyForecast) {
         this.dailyForecast = dailyForecast;
     }
     public List<DailyForecast> getDailyForecast() {
         return dailyForecast;
     }

    public void setStatus(String status) {
         this.status = status;
     }
     public String getStatus() {
         return status;
     }

}