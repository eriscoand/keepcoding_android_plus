package com.erisco.madridshops.domain.interactors.cachelist;

import android.support.annotation.NonNull;

import com.erisco.madridshops.domain.interactors.list.ListInteractorCompletion;
import com.erisco.madridshops.domain.managers.cache.list.ListCacheManagerCompletion;
import com.erisco.madridshops.domain.managers.cache.list.ListFromCacheManager;
import com.erisco.madridshops.domain.model.Activity.Activities;

public class ActivitiesListFromCacheInteractorImpl implements ListFromCacheInteractor{

    private ListFromCacheManager cacheManager;

    public ActivitiesListFromCacheInteractorImpl(final ListFromCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void execute(@NonNull final ListInteractorCompletion completion) {
        cacheManager.execute(new ListCacheManagerCompletion<Activities>() {
            @Override
            public void completion(@NonNull Activities activities) {
                completion.completion(activities);
            }
        });
    }
}
