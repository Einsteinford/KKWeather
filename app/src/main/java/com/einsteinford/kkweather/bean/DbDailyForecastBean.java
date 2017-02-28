package com.einsteinford.kkweather.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by kk on 17-2-28.
 */
@Entity
public class DbDailyForecastBean {
    @Id
    private Long id;
    //dailyForecast
    private long dailyId;
    private String date;
    private String hum;
    private String pcpn;
    private String pop;
    private String pres;
    private String uv;
    private String vis;
    //astro
    private String mr;
    private String ms;
    private String sr;
    private String ss;
    //cond
    private String code_d;
    private String code_n;
    private String txt_d;
    private String txt_n;
    //tmp
    private String max;
    private String min;
    //wind
    private String deg;
    private String dir;
    private String sc;
    private String spd;
    @Generated(hash = 2124927797)
    public DbDailyForecastBean(Long id, long dailyId, String date, String hum,
            String pcpn, String pop, String pres, String uv, String vis, String mr,
            String ms, String sr, String ss, String code_d, String code_n,
            String txt_d, String txt_n, String max, String min, String deg,
            String dir, String sc, String spd) {
        this.id = id;
        this.dailyId = dailyId;
        this.date = date;
        this.hum = hum;
        this.pcpn = pcpn;
        this.pop = pop;
        this.pres = pres;
        this.uv = uv;
        this.vis = vis;
        this.mr = mr;
        this.ms = ms;
        this.sr = sr;
        this.ss = ss;
        this.code_d = code_d;
        this.code_n = code_n;
        this.txt_d = txt_d;
        this.txt_n = txt_n;
        this.max = max;
        this.min = min;
        this.deg = deg;
        this.dir = dir;
        this.sc = sc;
        this.spd = spd;
    }
    @Generated(hash = 460681149)
    public DbDailyForecastBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public long getDailyId() {
        return this.dailyId;
    }
    public void setDailyId(long dailyId) {
        this.dailyId = dailyId;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getHum() {
        return this.hum;
    }
    public void setHum(String hum) {
        this.hum = hum;
    }
    public String getPcpn() {
        return this.pcpn;
    }
    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
    }
    public String getPop() {
        return this.pop;
    }
    public void setPop(String pop) {
        this.pop = pop;
    }
    public String getPres() {
        return this.pres;
    }
    public void setPres(String pres) {
        this.pres = pres;
    }
    public String getUv() {
        return this.uv;
    }
    public void setUv(String uv) {
        this.uv = uv;
    }
    public String getVis() {
        return this.vis;
    }
    public void setVis(String vis) {
        this.vis = vis;
    }
    public String getMr() {
        return this.mr;
    }
    public void setMr(String mr) {
        this.mr = mr;
    }
    public String getMs() {
        return this.ms;
    }
    public void setMs(String ms) {
        this.ms = ms;
    }
    public String getSr() {
        return this.sr;
    }
    public void setSr(String sr) {
        this.sr = sr;
    }
    public String getSs() {
        return this.ss;
    }
    public void setSs(String ss) {
        this.ss = ss;
    }
    public String getCode_d() {
        return this.code_d;
    }
    public void setCode_d(String code_d) {
        this.code_d = code_d;
    }
    public String getCode_n() {
        return this.code_n;
    }
    public void setCode_n(String code_n) {
        this.code_n = code_n;
    }
    public String getTxt_d() {
        return this.txt_d;
    }
    public void setTxt_d(String txt_d) {
        this.txt_d = txt_d;
    }
    public String getTxt_n() {
        return this.txt_n;
    }
    public void setTxt_n(String txt_n) {
        this.txt_n = txt_n;
    }
    public String getMax() {
        return this.max;
    }
    public void setMax(String max) {
        this.max = max;
    }
    public String getMin() {
        return this.min;
    }
    public void setMin(String min) {
        this.min = min;
    }
    public String getDeg() {
        return this.deg;
    }
    public void setDeg(String deg) {
        this.deg = deg;
    }
    public String getDir() {
        return this.dir;
    }
    public void setDir(String dir) {
        this.dir = dir;
    }
    public String getSc() {
        return this.sc;
    }
    public void setSc(String sc) {
        this.sc = sc;
    }
    public String getSpd() {
        return this.spd;
    }
    public void setSpd(String spd) {
        this.spd = spd;
    }
}
