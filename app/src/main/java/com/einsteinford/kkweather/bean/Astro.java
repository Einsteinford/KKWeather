package com.einsteinford.kkweather.bean;

import com.google.gson.annotations.Expose;

public class Astro {
    @Expose
    private String mr;
    @Expose
    private String ms;
    @Expose
    private String sr;
    @Expose
    private String ss;
    public void setMr(String mr) {
         this.mr = mr;
     }
     public String getMr() {
         return mr;
     }

    public void setMs(String ms) {
         this.ms = ms;
     }
     public String getMs() {
         return ms;
     }

    public void setSr(String sr) {
         this.sr = sr;
     }
     public String getSr() {
         return sr;
     }

    public void setSs(String ss) {
         this.ss = ss;
     }
     public String getSs() {
         return ss;
     }

}