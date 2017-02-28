package com.einsteinford.kkweather.bean;

import com.google.gson.annotations.Expose;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToOne;

@Entity
public class Update {
    @Id
    private Long id;
    @Expose
    private String loc;
    @Expose
    private String utc;
    @Generated(hash = 976121775)
    public Update(Long id, String loc, String utc) {
        this.id = id;
        this.loc = loc;
        this.utc = utc;
    }
    @Generated(hash = 1901255931)
    public Update() {
    }
    public void setLoc(String loc) {
         this.loc = loc;
     }
     public String getLoc() {
         return loc;
     }

    public void setUtc(String utc) {
         this.utc = utc;
     }
     public String getUtc() {
         return utc;
     }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}