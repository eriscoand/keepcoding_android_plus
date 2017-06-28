package com.erisco.madridshops.domain.managers.cache.save;

import android.content.Context;

import java.lang.ref.WeakReference;

import com.erisco.madridshops.domain.managers.db.ShopDAO.ShopDAO;
import com.erisco.madridshops.domain.model.Shop.Shop;
import com.erisco.madridshops.domain.model.Shop.Shops;

public class ShopsSaveListIntoCacheManagerDAOImpl implements SaveListIntoCacheManager<Shops> {

    private WeakReference<Context> contextWeakReference;

    public ShopsSaveListIntoCacheManagerDAOImpl(Context contextWeakReference) {
        this.contextWeakReference = new WeakReference<Context>(contextWeakReference);
    }

    @Override
    public void execute(Shops list, Runnable completion) {
        ShopDAO dao = new ShopDAO(contextWeakReference.get());
        for (Shop shop : list.allElements()) {
            dao.insert(shop);
        }
        completion.run();
    }
}
