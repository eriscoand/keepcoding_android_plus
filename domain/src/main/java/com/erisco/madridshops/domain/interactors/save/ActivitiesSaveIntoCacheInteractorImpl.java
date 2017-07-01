package com.erisco.madridshops.domain.interactors.save;

import com.erisco.madridshops.domain.managers.cache.save.SaveListIntoCacheManager;
import com.erisco.madridshops.domain.model.Activity.Activities;
import com.erisco.madridshops.domain.model.Shop.Shops;

public class ActivitiesSaveIntoCacheInteractorImpl implements SaveIntoCacheInteractor<Activities> {

    private SaveListIntoCacheManager manager;

    public ActivitiesSaveIntoCacheInteractorImpl(SaveListIntoCacheManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(Activities activities, Runnable completion) {
        manager.execute(activities, completion);
    }
}
