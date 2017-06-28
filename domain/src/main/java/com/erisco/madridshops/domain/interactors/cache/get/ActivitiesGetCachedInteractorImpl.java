package com.erisco.madridshops.domain.interactors.cache.get;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.erisco.madridshops.domain.interactors.cache.GetCachedInteractor;
import com.erisco.madridshops.domain.interactors.cache.SetCachedInteractor;

import java.lang.ref.WeakReference;

public class ActivitiesGetCachedInteractorImpl implements GetCachedInteractor {

    private WeakReference<Context> context;

    public ActivitiesGetCachedInteractorImpl(Context context) {
        this.context = new WeakReference<Context>(context);
    }

    @Override
    public void execute(Runnable Cached, Runnable NotCached) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.get());
        boolean activitiesSaved = preferences.getBoolean(SetCachedInteractor.ACTIVITIES_SAVED, false);

        if (activitiesSaved) {
            Cached.run();
        } else {
            NotCached.run();
        }
    }
}
