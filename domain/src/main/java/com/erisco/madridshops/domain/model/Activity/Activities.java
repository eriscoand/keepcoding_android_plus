package com.erisco.madridshops.domain.model.Activity;

import android.support.annotation.NonNull;

import com.erisco.madridshops.domain.model.Iterable;
import com.erisco.madridshops.domain.model.Queryable;
import com.erisco.madridshops.domain.model.Updatable;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Activities implements Iterable<Activity>, Updatable<Activity>, Queryable<Activity> {
    private List<Activity> activities;

    public static Activities from(@NonNull final List<Activity> activitiesList) {
        final Activities activities = new Activities();

        for (final Activity activity : activitiesList) {
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

    public List<Activity> query(String query) {

        List<Activity> returnActivities = new LinkedList<>();

        for (Activity activity : this.activities) {

            String name = activity.getName();
            name = name.replace(" ", ""); //DELETING BLANK SPACES
            name = name.toLowerCase(); //LOWER CASE

            int i = name.indexOf(query);
            if (i >= 0) {
                returnActivities.add(activity);
            }
        }
        return returnActivities;

    }

}
