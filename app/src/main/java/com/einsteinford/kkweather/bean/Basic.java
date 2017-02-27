package com.einsteinford.kkweather.bean;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Basic {

    @NotNull
    private String city;
    private String cnty;
    private String lat;
    private String lon;
    @SerializedName("id")
    private String basic_id;
    @Transient
    private Update update;

    @Generated(hash = 1536475543)
    public Basic(@NotNull String city, String cnty, String lat, String lon,
            String basic_id) {
        this.city = city;
        this.cnty = cnty;
        this.lat = lat;
        this.lon = lon;
        this.basic_id = basic_id;
    }
    @Generated(hash = 414549035)
    public Basic() {
    }

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