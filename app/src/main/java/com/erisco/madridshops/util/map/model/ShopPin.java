package com.erisco.madridshops.util.map.model;

import java.util.ArrayList;
import java.util.List;

import com.erisco.madridshops.domain.model.Shop.Shop;
import com.erisco.madridshops.domain.model.Shop.Shops;
import com.erisco.madridshops.util.map.MapPinnable;

public class ShopPin implements MapPinnable<Shop> {
    private Shop shop;

    public static List<MapPinnable> shopPinsFromShops(Shops shops) {
        List<Shop> shopList = shops.allElements();
        List<MapPinnable> shopPinList = new ArrayList<>();

        for (Shop shop : shopList) {
            ShopPin sp = new ShopPin(shop);
            shopPinList.add(sp);
        }

        return shopPinList;
    }

    public ShopPin(Shop shop) {
        this.shop = shop;
    }

    @Override
    public float getLatitude() {
        return shop.getLatitude();
    }

    @Override
    public float getLongitude() {
        return shop.getLongitude();
    }

    @Override
    public String getPinDescription() {
        return shop.getName() + " - " + shop.getAddress();
    }

    @Override
    public String getPinImageUrl() {
        return shop.getLogoUrl();
    }

    @Override
    public Shop getRelatedModelObject() {
        return shop;
    }
}
