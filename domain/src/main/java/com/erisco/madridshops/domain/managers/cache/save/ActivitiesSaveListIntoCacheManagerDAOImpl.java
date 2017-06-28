package com.erisco.madridshops.domain.managers.cache.save;

import android.content.Context;

import com.erisco.madridshops.domain.managers.db.ActivityDAO.ActivityDAO;
import com.erisco.madridshops.domain.managers.db.ShopDAO.ShopDAO;
import com.erisco.madridshops.domain.model.Activity.Activities;
import com.erisco.madridshops.domain.model.Activity.Activity;
import com.erisco.madridshops.domain.model.Shop.Shop;
import com.erisco.madridshops.domain.model.Shop.Shops;

import java.lang.ref.WeakReference;

public class ActivitiesSaveListIntoCacheManagerDAOImpl implements SaveListIntoCacheManager<Activities> {

    private WeakReference<Context> contextWeakReference;

    public ActivitiesSaveListIntoCacheManagerDAOImpl(Context contextWeakReference) {
        this.contextWeakReference = new WeakReference<Context>(contextWeakReference);
    }

    @Override
    public void execute(Activities list, Runnable completion) {
        ActivityDAO dao = new ActivityDAO(contextWeakReference.get());
        for (Activity activity : list.allElements()) {
            dao.insert(activity);
        }
        completion.run();
    }
}
