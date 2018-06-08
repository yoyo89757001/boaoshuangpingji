package com.xiaojun.boaoshuangpingji.beans;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.xiaojun.boaoshuangpingji.beans.BaoCunBean;

import com.xiaojun.boaoshuangpingji.beans.BaoCunBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig baoCunBeanDaoConfig;

    private final BaoCunBeanDao baoCunBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        baoCunBeanDaoConfig = daoConfigMap.get(BaoCunBeanDao.class).clone();
        baoCunBeanDaoConfig.initIdentityScope(type);

        baoCunBeanDao = new BaoCunBeanDao(baoCunBeanDaoConfig, this);

        registerDao(BaoCunBean.class, baoCunBeanDao);
    }
    
    public void clear() {
        baoCunBeanDaoConfig.clearIdentityScope();
    }

    public BaoCunBeanDao getBaoCunBeanDao() {
        return baoCunBeanDao;
    }

}
