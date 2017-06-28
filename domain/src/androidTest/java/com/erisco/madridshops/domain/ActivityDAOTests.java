package com.erisco.madridshops.domain;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.erisco.madridshops.domain.managers.db.ActivityDAO.ActivityDAO;
import com.erisco.madridshops.domain.model.Activity.Activity;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ActivityDAOTests {
    final static Context appContext = InstrumentationRegistry.getTargetContext();
    public static final int TEST_ID = 888;
    public static final String TEST_NAME = "name";

    @Test
    public void given_activity_DAO_inserts_activity() throws Exception {
        ActivityDAO sut = new ActivityDAO(appContext);
        Activity activity = Activity.of(1, "Activity test").
                        setAddress("address").
                        setLatitude(10).
                        setLongitude(11);

        long id = sut.insert(activity);
        assertTrue(id > 0);

    }

    @Test
    public void given_inserted_activities_DAO_queries_all_activities() throws Exception {
        ActivityDAO sut = new ActivityDAO(appContext);
        Activity activity = insertActivity(sut, 1, "Activity test", "address", 10, 11);

        List<Activity> activities = sut.query();

        assertNotNull(activities);
        assertTrue(activities.size() >= 1);
    }

    @Test
    public void given_inserted_activities_deleteall_returns_empty_table() throws Exception {
        ActivityDAO sut = new ActivityDAO(appContext);

        insertActivities();

        sut.deleteAll();

        List<Activity> activityList = sut.query();

        assertNull(activityList);
    }

    @Test
    public void given_one_inserted_activity_I_can_delete_that_activity() throws Exception {
        ActivityDAO sut = new ActivityDAO(appContext);

        sut.deleteAll();
        Activity insertedActivity = insertActivity(sut, TEST_ID, TEST_NAME, "", 10, 10);
        Activity activity = sut.query(insertedActivity.getId());

        assertNotNull(activity);
        assertEquals(insertedActivity.getId(), activity.getId());
        assertEquals(TEST_NAME, activity.getName());
    }


    private Activity insertActivity(ActivityDAO sut, long id, String name, String address, float latitude, float longitude) {
        Activity activity = Activity.of(id, name).
                setAddress(address).
                setLatitude(latitude).
                setLongitude(latitude);

        long insertedId = sut.insert(activity);
        return activity;
    }

    private void insertActivities() {
        // Context appContext = InstrumentationRegistry.getTargetContext();
        ActivityDAO sut = new ActivityDAO(appContext);
        for (int i = 0; i < 10; i++) {
            insertActivity(sut, i, "Activity " + i, "Address " + i, i+1, i);
        }
    }
}
