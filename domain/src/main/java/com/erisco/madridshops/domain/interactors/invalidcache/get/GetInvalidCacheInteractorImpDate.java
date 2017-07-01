package com.erisco.madridshops.domain.interactors.invalidcache.get;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.erisco.madridshops.domain.interactors.cache.SetCachedInteractor;
import com.erisco.madridshops.domain.interactors.invalidcache.GetInvalidCacheInteractor;
import com.erisco.madridshops.domain.interactors.invalidcache.SetInvalidCacheInteractor;

import java.lang.ref.WeakReference;
import java.util.Calendar;

public class GetInvalidCacheInteractorImpDate implements GetInvalidCacheInteractor {

    public static final int DAYS_TO_INVALIDATE = 7;

    private WeakReference<Context> context;

    public GetInvalidCacheInteractorImpDate(Context context) {
        this.context = new WeakReference<Context>(context);
    }


    @Override
    public void execute(Runnable Valid, Runnable NotValid) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.get());
        long dateSaved = preferences.getLong(SetInvalidCacheInteractor.CACHE_INVALID_INDICATOR, 0);

        Calendar calendar = Calendar.getInstance();

        Calendar addedDate = Calendar.getInstance();
        addedDate.setTimeInMillis(dateSaved);
        addedDate.add(Calendar.DAY_OF_MONTH, DAYS_TO_INVALIDATE);

        if (calendar.compareTo(addedDate) <= 0) {
            Valid.run();
        } else {
            NotValid.run();
        }

    }
}
