package com.einsteinford.kkweather.bean;

import com.google.gson.annotations.Expose;

public class Tmp {
    @Expose
    private String max;
    @Expose
    private String min;
    public void setMax(String max) {
         this.max = max;
     }
     public String getMax() {
         return max;
     }

    public void setMin(String min) {
         this.min = min;
     }
     public String getMin() {
         return min;
     }

}