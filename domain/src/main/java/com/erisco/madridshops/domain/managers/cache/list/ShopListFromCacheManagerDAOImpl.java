package com.erisco.madridshops.domain.managers.cache.list;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.List;

import com.erisco.madridshops.domain.managers.db.ShopDAO.ShopDAO;
import com.erisco.madridshops.domain.model.Shop.Shop;
import com.erisco.madridshops.domain.model.Shop.Shops;

public class ShopListFromCacheManagerDAOImpl implements ListFromCacheManager<Shops> {

    private WeakReference<Context> contextWeakReference;

    public ShopListFromCacheManagerDAOImpl(Context contextWeakReference) {
        this.contextWeakReference = new WeakReference<Context>(contextWeakReference);
    }

    @Override
    public void execute(@NonNull final ListCacheManagerCompletion completion) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ShopDAO dao = new ShopDAO(contextWeakReference.get());
                List<Shop> shopList = dao.query();
                if (shopList == null) {
                    return;
                }
                final Shops shops = Shops.from(shopList);

                Handler uiHandler = new Handler(Looper.getMainLooper());
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        completion.completion(shops);
                    }
                });
            }
        }).start();
    }
}
