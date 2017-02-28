package com.einsteinford.kkweather.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.einsteinford.kkweather.bean.DbDailyForecastBean;
import com.einsteinford.kkweather.bean.DbBasicBean;

import com.einsteinford.kkweather.db.DbDailyForecastBeanDao;
import com.einsteinford.kkweather.db.DbBasicBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig dbDailyForecastBeanDaoConfig;
    private final DaoConfig dbBasicBeanDaoConfig;

    private final DbDailyForecastBeanDao dbDailyForecastBeanDao;
    private final DbBasicBeanDao dbBasicBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        dbDailyForecastBeanDaoConfig = daoConfigMap.get(DbDailyForecastBeanDao.class).clone();
        dbDailyForecastBeanDaoConfig.initIdentityScope(type);

        dbBasicBeanDaoConfig = daoConfigMap.get(DbBasicBeanDao.class).clone();
        dbBasicBeanDaoConfig.initIdentityScope(type);

        dbDailyForecastBeanDao = new DbDailyForecastBeanDao(dbDailyForecastBeanDaoConfig, this);
        dbBasicBeanDao = new DbBasicBeanDao(dbBasicBeanDaoConfig, this);

        registerDao(DbDailyForecastBean.class, dbDailyForecastBeanDao);
        registerDao(DbBasicBean.class, dbBasicBeanDao);
    }
    
    public void clear() {
        dbDailyForecastBeanDaoConfig.clearIdentityScope();
        dbBasicBeanDaoConfig.clearIdentityScope();
    }

    public DbDailyForecastBeanDao getDbDailyForecastBeanDao() {
        return dbDailyForecastBeanDao;
    }

    public DbBasicBeanDao getDbBasicBeanDao() {
        return dbBasicBeanDao;
    }

}
