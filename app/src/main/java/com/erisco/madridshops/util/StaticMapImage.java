package com.erisco.madridshops.util;

import android.support.annotation.NonNull;

import com.erisco.madridshops.domain.model.Shop.Shop;

public class StaticMapImage {
    public static String getMapImageUrl(@NonNull final double lat, @NonNull final double lng) {
        String url = String.format("http://maps.googleapis.com/maps/api/staticmap?center=%f,%f&zoom=17&size=320x220",
                lat, lng);

        return url;
    }
}
