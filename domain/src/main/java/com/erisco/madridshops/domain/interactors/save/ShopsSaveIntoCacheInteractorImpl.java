package com.erisco.madridshops.domain.interactors.save;

import com.erisco.madridshops.domain.managers.cache.save.SaveListIntoCacheManager;
import com.erisco.madridshops.domain.model.Shop.Shops;

public class ShopsSaveIntoCacheInteractorImpl implements SaveIntoCacheInteractor<Shops> {

    private SaveListIntoCacheManager manager;

    public ShopsSaveIntoCacheInteractorImpl(SaveListIntoCacheManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(Shops shops, Runnable completion) {
        manager.execute(shops, completion);
    }
}
