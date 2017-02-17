package com.einsteinford.kkweather.bean;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Auto-generated: 2017-02-16 17:2:47
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class WeatherRootBean {

    @SerializedName("HeWeather5")
    private List<Heweather5> heweather5;
    public void setHeweather5(List<Heweather5> heweather5) {
         this.heweather5 = heweather5;
     }
     public List<Heweather5> getHeweather5() {
         return heweather5;
     }

}