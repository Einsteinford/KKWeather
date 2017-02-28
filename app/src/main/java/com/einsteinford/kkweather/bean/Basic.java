package com.einsteinford.kkweather.bean;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

public class Basic {

    private String city;
    private String cnty;
    private String lat;
    private String lon;
    private String basic_id;
    private Update update;

    public void setCity(String city) {
         this.city = city;
     }
     public String getCity() {
         return city;
     }

    public void setCnty(String cnty) {
         this.cnty = cnty;
     }
     public String getCnty() {
         return cnty;
     }

    public void setBasic_id(String basic_id) {
         this.basic_id = basic_id;
     }
     public String getBasic_id() {
         return basic_id;
     }

    public void setLat(String lat) {
         this.lat = lat;
     }
     public String getLat() {
         return lat;
     }

    public void setLon(String lon) {
         this.lon = lon;
     }
     public String getLon() {
         return lon;
     }

    public void setUpdate(Update update) {
         this.update = update;
     }
     public Update getUpdate() {
         return update;
     }

}