package com.erisco.madridshops.domain.interactors.invalidcache.set;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.erisco.madridshops.domain.interactors.invalidcache.SetInvalidCacheInteractor;

import java.lang.ref.WeakReference;
import java.util.Calendar;

public class SetInvalidCacheInteractorImpDate implements SetInvalidCacheInteractor {
    private WeakReference<Context> context;

    public SetInvalidCacheInteractorImpDate(Context context) {
        this.context = new WeakReference<Context>(context);
    }
    @Override
    public void execute() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.get());
        SharedPreferences.Editor editor = preferences.edit();

        long time = Calendar.getInstance().getTime().getTime();

        editor.putLong(SetInvalidCacheInteractor.CACHE_INVALID_INDICATOR, time);

        editor.commit();

    }
}
