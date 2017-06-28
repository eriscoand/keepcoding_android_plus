package com.erisco.madridshops.domain.interactors.cache.set;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.erisco.madridshops.domain.interactors.cache.SetCachedInteractor;

import java.lang.ref.WeakReference;

public class ShopsSetCachedInteractorImpl implements SetCachedInteractor {
    private WeakReference<Context> context;

    public ShopsSetCachedInteractorImpl(Context context) {
        this.context = new WeakReference<Context>(context);
    }

    @Override
    public void execute(boolean saved) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.get());
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean(ShopsSetCachedInteractorImpl.SHOPS_SAVED, saved);

        editor.commit();
    }
}
