package com.erisco.madridshops.domain.interactors;

import com.erisco.madridshops.domain.managers.cache.save.SaveListIntoCacheManager;
import com.erisco.madridshops.domain.model.Shop.Shops;

public class SaveAllShopsIntoCacheInteractorImpl implements SaveAllShopsIntoCacheInteractor {

    private SaveListIntoCacheManager manager;

    public SaveAllShopsIntoCacheInteractorImpl(SaveListIntoCacheManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(Shops shops, Runnable completion) {
        manager.execute(shops, completion);
    }
}
