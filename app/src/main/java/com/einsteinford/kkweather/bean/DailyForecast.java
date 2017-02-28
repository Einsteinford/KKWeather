package com.einsteinford.kkweather.bean;

import com.google.gson.annotations.Expose;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

public class DailyForecast {
    @Id
    private Long id;
    @Expose
    private Astro astro;
    @Expose
    private Cond cond;
    @Expose
    private Tmp tmp;
    @Expose
    private Wind wind;
    @Expose
    private Date date;
    @Expose
    private String hum;
    @Expose
    private String pcpn;
    @Expose
    private String pop;
    @Expose
    private String pres;
    @Expose
    private String uv;
    @Expose
    private String vis;

    public void setAstro(Astro astro) {
        this.astro = astro;
    }

    public Astro getAstro() {
        return astro;
    }

    public void setCond(Cond cond) {
        this.cond = cond;
    }

    public Cond getCond() {
        return cond;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getHum() {
        return hum;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
    }

    public String getPcpn() {
        return pcpn;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getPop() {
        return pop;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public String getPres() {
        return pres;
    }

    public void setTmp(Tmp tmp) {
        this.tmp = tmp;
    }

    public Tmp getTmp() {
        return tmp;
    }

    public void setUv(String uv) {
        this.uv = uv;
    }

    public String getUv() {
        return uv;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public String getVis() {
        return vis;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Wind getWind() {
        return wind;
    }

}