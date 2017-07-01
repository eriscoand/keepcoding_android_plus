package com.erisco.madridactivities.domain;

import com.erisco.madridshops.domain.model.Activity.Activity;
import com.erisco.madridshops.domain.model.Activity.Activities;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ActivitiesUnitTests {
    @Test
    public void after_creation_activities_size_is_zero() throws Exception {
        Activities sut = new Activities();

        assertEquals(0, sut.size());
    }

    @Test
    public void activities_adding_one_activity_size_is_one() throws Exception {
        Activities sut = new Activities();

        sut.add(Activity.of(1, "My activity"));

        assertEquals(1, sut.size());
    }

    @Test
    public void activities_adding_one_activity_and_deleting_size_is_zero() throws Exception {
        Activities sut = new Activities();

        Activity activity = Activity.of(1, "My activity");
        sut.add(activity);
        sut.delete(activity);

        assertEquals(0, sut.size());
    }

    @Test
    public void activities_adding_one_activity_and_getting_returns_that_activity() throws Exception {
        Activities sut = new Activities();

        Activity activity = Activity.of(1, "My activity");
        sut.add(activity);
        Activity activity1 = sut.get(0);

        assertEquals(activity.getId(), activity1.getId());
        assertEquals(activity.getName(), activity1.getName());
    }

    @Test
    public void activities_adding_several_activities_returns_all_activities() throws Exception {
        Activities sut = new Activities();

        for (int i = 0; i < 10; i++) {
            Activity activity = Activity.of(i, "My activity " + i);
            sut.add(activity);
        }

        assertEquals(10, sut.size());
        assertEquals(10, sut.allElements().size());
        assertEquals(0, sut.allElements().get(0).getId());
        assertEquals("My activity 0", sut.allElements().get(0).getName());
    }

    @Test
    public void activities_adding_several_activities_query_returns_good_count() throws Exception {
        Activities sut = new Activities();

        for (int i = 0; i < 10; i++) {
            Activity activity = Activity.of(i, "My activity " + i);
            sut.add(activity);
        }

        Activity activity1 = Activity.of(11, "aa aa");
        sut.add(activity1);
        Activity activity2 = Activity.of(11, "aabaa");
        sut.add(activity2);
        Activity activity3 = Activity.of(11, "aaa");
        sut.add(activity3);
        Activity activity4 = Activity.of(11, "aa aa");
        sut.add(activity4);

        assertEquals(4, sut.query("aa").size());
        assertEquals(1, sut.query("aab").size());
        assertEquals(10, sut.query("activity").size());
    }
}