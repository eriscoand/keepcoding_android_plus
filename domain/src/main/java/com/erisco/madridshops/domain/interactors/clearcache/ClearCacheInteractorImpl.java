package com.erisco.madridshops.domain.interactors.clearcache;

import com.erisco.madridshops.domain.managers.cache.clear.ClearCacheManager;

public class ClearCacheInteractorImpl implements ClearCacheInteractor {
    private ClearCacheManager manager;

    public ClearCacheInteractorImpl(ClearCacheManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(Runnable completion) {
        manager.execute(completion);
    }
}
