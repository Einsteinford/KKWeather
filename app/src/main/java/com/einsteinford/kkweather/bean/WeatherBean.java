package com.einsteinford.kkweather.bean;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherBean {
    @Expose
    @SerializedName("HeWeather5")
    private List<Heweather5> heweather5;
    public void setHeWeather5(List<Heweather5> heweather5) {
         this.heweather5 = heweather5;
     }
     public List<Heweather5> getHeWeather5() {
         return heweather5;
     }

}