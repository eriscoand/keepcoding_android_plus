package com.erisco.madridshops.domain.interactors.cache.get;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.erisco.madridshops.domain.interactors.cache.GetCachedInteractor;
import com.erisco.madridshops.domain.interactors.cache.SetCachedInteractor;

import java.lang.ref.WeakReference;

public class ShopsGetCachedInteractorImpl implements GetCachedInteractor {

    private WeakReference<Context> context;

    public ShopsGetCachedInteractorImpl(Context context) {
        this.context = new WeakReference<Context>(context);
    }

    @Override
    public void execute(Runnable Cached, Runnable NotCached) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.get());
        boolean shopsSaved = preferences.getBoolean(SetCachedInteractor.SHOPS_SAVED, false);

        if (shopsSaved) {
            Cached.run();
        } else {
            NotCached.run();
        }
    }
}
