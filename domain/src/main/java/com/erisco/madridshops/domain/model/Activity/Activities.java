package com.erisco.madridshops.domain.model.Activity;

import android.support.annotation.NonNull;

import com.erisco.madridshops.domain.model.Iterable;
import com.erisco.madridshops.domain.model.Updatable;

import java.util.LinkedList;
import java.util.List;

public class Activities implements Iterable<Activity>, Updatable<Activity> {
    private List<Activity> activities;

    public static Activities from(@NonNull final List<Activity> shopList) {
        final Activities activities = new Activities();

        for (final Activity activity : shopList) {
            activities.add(activity);
        }

        return activities;
    }

    public Activities() {
    }

    // lazy getter
    private List<Activity> getActivities() {
        if (activities == null) {
            activities =  new LinkedList<>();
        }
        return activities;
    }

    @Override
    public long size() {
        return getActivities().size();
    }

    @Override
    public Activity get(long index) {
        return getActivities().get((int)index);
    }

    @Override
    public List<Activity> allElements() {
        List<Activity> listCopy = new LinkedList<>();
        for (Activity activity : getActivities()) {
            listCopy.add(activity);
        }

        return listCopy;
    }

    @Override
    public void add(Activity activity) {
        getActivities().add(activity);
    }

    @Override
    public void delete(Activity activity) {
        getActivities().remove(activity);
    }

    @Override
    public void update(Activity activity, long index) {
        getActivities().set((int)index, activity);
    }
}
