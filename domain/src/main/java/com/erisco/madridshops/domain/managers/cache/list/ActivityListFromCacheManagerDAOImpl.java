package com.erisco.madridshops.domain.managers.cache.list;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.erisco.madridshops.domain.managers.db.ActivityDAO.ActivityDAO;
import com.erisco.madridshops.domain.model.Activity.Activities;
import com.erisco.madridshops.domain.model.Activity.Activity;

import java.lang.ref.WeakReference;
import java.util.List;

public class ActivityListFromCacheManagerDAOImpl implements ListFromCacheManager<Activities> {

    private WeakReference<Context> contextWeakReference;

    public ActivityListFromCacheManagerDAOImpl(Context contextWeakReference) {
        this.contextWeakReference = new WeakReference<Context>(contextWeakReference);
    }

    @Override
    public void execute(@NonNull final ListCacheManagerCompletion completion) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ActivityDAO dao = new ActivityDAO(contextWeakReference.get());
                List<Activity> activityList = dao.query();
                if (activityList == null) {
                    return;
                }
                final Activities activities = Activities.from(activityList);

                Handler uiHandler = new Handler(Looper.getMainLooper());
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        completion.completion(activities);
                    }
                });
            }
        }).start();
    }
}
