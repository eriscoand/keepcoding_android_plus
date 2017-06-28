package com.erisco.madridshops.domain.managers.network.mappers;

import android.util.Log;

import java.util.List;

import com.erisco.madridshops.domain.managers.network.entities.JsonEntity;
import com.erisco.madridshops.domain.model.Shop.Shop;
import com.erisco.madridshops.domain.model.Shop.Shops;

public class ShopsMapper implements Mapper<Shops> {

    public ShopsMapper(){}

    @Override
    public Shops map(List<JsonEntity> entities) {
        Shops shops = new Shops();

        for (JsonEntity shopEntity : entities) {
            Shop shop = Shop.of(shopEntity.getId(), shopEntity.getName());

            shop.setDescription(shopEntity.getDescription_es());
            shop.setLatitude(CorrectCoordinate.getCorrectCoordinateComponent(shopEntity.getGps_lat()));
            shop.setLongitude(CorrectCoordinate.getCorrectCoordinateComponent(shopEntity.getGps_lon()));
            shop.setAddress(shopEntity.getAddress());
            shop.setUrl(shopEntity.getUrl());
            shop.setImageUrl(shopEntity.getImg());
            shop.setLogoUrl(shopEntity.getLogo_img());

            shops.add(shop);
        }

        return shops;
    }


}