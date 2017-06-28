package com.erisco.madridshops.domain.interactors.cache.set;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.erisco.madridshops.domain.interactors.cache.SetCachedInteractor;

import java.lang.ref.WeakReference;

public class ActivitiesSetCachedInteractorImpl implements SetCachedInteractor {
    private WeakReference<Context> context;

    public ActivitiesSetCachedInteractorImpl(Context context) {
        this.context = new WeakReference<Context>(context);
    }

    @Override
    public void execute(boolean saved) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.get());
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean(ActivitiesSetCachedInteractorImpl.ACTIVITIES_SAVED, saved);

        editor.commit();
    }
}
