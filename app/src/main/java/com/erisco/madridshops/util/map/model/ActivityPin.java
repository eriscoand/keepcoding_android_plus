package com.erisco.madridshops.util.map.model;

import com.erisco.madridshops.domain.model.Activity.Activity;
import com.erisco.madridshops.domain.model.Activity.Activities;
import com.erisco.madridshops.util.map.MapPinnable;

import java.util.ArrayList;
import java.util.List;

public class ActivityPin implements MapPinnable<Activity> {
    private Activity activity;

    public static List<MapPinnable> activityPinsFromActivities(Activities activities) {
        List<Activity> activityList = activities.allElements();
        List<MapPinnable> activityPinList = new ArrayList<>();

        for (Activity activity : activityList) {
            ActivityPin sp = new ActivityPin(activity);
            activityPinList.add(sp);
        }

        return activityPinList;
    }

    public ActivityPin(Activity activity) {
        this.activity = activity;
    }

    @Override
    public float getLatitude() {
        return activity.getLatitude();
    }

    @Override
    public float getLongitude() {
        return activity.getLongitude();
    }

    @Override
    public String getPinDescription() {
        return activity.getName() + " - " + activity.getAddress();
    }

    @Override
    public String getPinImageUrl() {
        return activity.getLogoUrl();
    }

    @Override
    public Activity getRelatedModelObject() {
        return activity;
    }
}
