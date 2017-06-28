package com.erisco.madridshops.domain.managers.network.mappers;

import com.erisco.madridshops.domain.managers.network.entities.JsonEntity;
import com.erisco.madridshops.domain.model.Activity.Activities;
import com.erisco.madridshops.domain.model.Activity.Activity;

import java.util.List;

public class ActivitiesMapper implements Mapper<Activities> {

    public ActivitiesMapper(){}

    @Override
    public Activities map(List<JsonEntity> entities) {
        Activities activities = new Activities();

        for (JsonEntity shopEntity : entities) {
            Activity activity = Activity.of(shopEntity.getId(), shopEntity.getName());

            activity.setDescription(shopEntity.getDescription_es());
            activity.setLatitude(CorrectCoordinate.getCorrectCoordinateComponent(shopEntity.getGps_lat()));
            activity.setLongitude(CorrectCoordinate.getCorrectCoordinateComponent(shopEntity.getGps_lon()));
            activity.setAddress(shopEntity.getAddress());
            activity.setUrl(shopEntity.getUrl());
            activity.setImageUrl(shopEntity.getImg());
            activity.setLogoUrl(shopEntity.getLogo_img());

            activities.add(activity);
        }

        return activities;
    }


}