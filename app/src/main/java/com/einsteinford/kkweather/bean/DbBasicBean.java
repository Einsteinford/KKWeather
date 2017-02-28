package com.einsteinford.kkweather.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.einsteinford.kkweather.db.DaoSession;
import com.einsteinford.kkweather.db.DbDailyForecastBeanDao;
import com.einsteinford.kkweather.db.DbBasicBeanDao;

/**
 * Created by kk on 17-2-28.
 */
@Entity
public class DbBasicBean {
    @Id
    private Long id;
    //basic
    @Unique
    private String basic_id;

    private String city;
    private String cnty;
    private String lat;
    private String lon;
    //update
    private String loc;
    private String utc;
    @ToMany(referencedJoinProperty = "dailyId")
    @OrderBy("date ASC")
    List<DbDailyForecastBean> dailyForecastBeans;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1662811455)
    private transient DbBasicBeanDao myDao;
    @Generated(hash = 386213082)
    public DbBasicBean(Long id, String basic_id, String city, String cnty,
            String lat, String lon, String loc, String utc) {
        this.id = id;
        this.basic_id = basic_id;
        this.city = city;
        this.cnty = cnty;
        this.lat = lat;
        this.lon = lon;
        this.loc = loc;
        this.utc = utc;
    }
    @Generated(hash = 1775557829)
    public DbBasicBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBasic_id() {
        return this.basic_id;
    }
    public void setBasic_id(String basic_id) {
        this.basic_id = basic_id;
    }
    public String getCity() {
        return this.city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCnty() {
        return this.cnty;
    }
    public void setCnty(String cnty) {
        this.cnty = cnty;
    }
    public String getLat() {
        return this.lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }
    public String getLon() {
        return this.lon;
    }
    public void setLon(String lon) {
        this.lon = lon;
    }
    public String getLoc() {
        return this.loc;
    }
    public void setLoc(String loc) {
        this.loc = loc;
    }
    public String getUtc() {
        return this.utc;
    }
    public void setUtc(String utc) {
        this.utc = utc;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 2043184366)
    public List<DbDailyForecastBean> getDailyForecastBeans() {
        if (dailyForecastBeans == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DbDailyForecastBeanDao targetDao = daoSession
                    .getDbDailyForecastBeanDao();
            List<DbDailyForecastBean> dailyForecastBeansNew = targetDao
                    ._queryDbBasicBean_DailyForecastBeans(id);
            synchronized (this) {
                if (dailyForecastBeans == null) {
                    dailyForecastBeans = dailyForecastBeansNew;
                }
            }
        }
        return dailyForecastBeans;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 2048925548)
    public synchronized void resetDailyForecastBeans() {
        dailyForecastBeans = null;
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
    @Generated(hash = 1760583590)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getDbBasicBeanDao() : null;
    }
}
