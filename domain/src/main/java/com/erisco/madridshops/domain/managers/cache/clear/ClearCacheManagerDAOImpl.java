package com.erisco.madridshops.domain.managers.cache.clear;

import android.content.Context;

import java.lang.ref.WeakReference;

import com.erisco.madridshops.domain.managers.db.ShopDAO.ShopDAO;
import com.erisco.madridshops.domain.managers.db.ActivityDAO.ActivityDAO;

public class ClearCacheManagerDAOImpl implements ClearCacheManager {
    private WeakReference<Context> contextWeakReference;

    public ClearCacheManagerDAOImpl(Context contextWeakReference) {
        this.contextWeakReference = new WeakReference<Context>(contextWeakReference);
    }


    @Override
    public void execute(Runnable completion) {
        ShopDAO shopDao = new ShopDAO(contextWeakReference.get());
        shopDao.deleteAll();

        ActivityDAO activityDAO = new ActivityDAO(contextWeakReference.get());
        activityDAO.deleteAll();

        completion.run();
    }
}
