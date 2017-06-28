package com.erisco.madridshops.domain.interactors.cachelist;

import android.support.annotation.NonNull;

import com.erisco.madridshops.domain.interactors.list.ListInteractorCompletion;
import com.erisco.madridshops.domain.managers.cache.list.ListFromCacheManager;
import com.erisco.madridshops.domain.managers.cache.list.ListCacheManagerCompletion;
import com.erisco.madridshops.domain.model.Shop.Shops;

public class ShopsListFromCacheInteractorImpl implements ListFromCacheInteractor{

    private ListFromCacheManager cacheManager;

    public ShopsListFromCacheInteractorImpl(final ListFromCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void execute(@NonNull final ListInteractorCompletion completion) {
        cacheManager.execute(new ListCacheManagerCompletion<Shops>() {
            @Override
            public void completion(@NonNull Shops shops) {
                completion.completion(shops);
            }
        });
    }
}
