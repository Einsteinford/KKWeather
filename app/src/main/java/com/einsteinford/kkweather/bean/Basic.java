package com.einsteinford.kkweather.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import com.einsteinford.kkweather.db.DaoSession;
import com.einsteinford.kkweather.db.UpdateDao;
import com.einsteinford.kkweather.db.BasicDao;

@Entity(indexes = {
        @Index(value = "city,cnty,lat,lon"), @Index(value = "basic_id DESC", unique = true)})
public class Basic {
    @Id
    private Long id;    //数据库主键
    @Expose
    private String city;
    @Expose
    private String cnty;
    @Expose
    private String lat;
    @Expose
    private String lon;
    @NotNull
    @Expose
    @SerializedName("id")
    private String basic_id;
    private long updateId;
    @Expose
    @ToOne(joinProperty = "updateId")
    private Update update;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1188525788)
    private transient BasicDao myDao;
    @Generated(hash = 1668870327)
    private transient Long update__resolvedKey;

    @Generated(hash = 792033208)
    public Basic(Long id, String city, String cnty, String lat, String lon,
            @NotNull String basic_id, long updateId) {
        this.id = id;
        this.city = city;
        this.cnty = cnty;
        this.lat = lat;
        this.lon = lon;
        this.basic_id = basic_id;
        this.updateId = updateId;
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 852694485)
    public Update getUpdate() {
        long __key = this.updateId;
        if (update__resolvedKey == null || !update__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UpdateDao targetDao = daoSession.getUpdateDao();
            Update updateNew = targetDao.load(__key);
            synchronized (this) {
                update = updateNew;
                update__resolvedKey = __key;
            }
        }
        return update;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2084187426)
    public void setUpdate(@NotNull Update update) {
        if (update == null) {
            throw new DaoException(
                    "To-one property 'updateId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.update = update;
            updateId = update.getId();
            update__resolvedKey = updateId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1421313158)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getBasicDao() : null;
    }

    public long getUpdateId() {
        return this.updateId;
    }

    public void setUpdateId(long updateId) {
        this.updateId = updateId;
    }

}