package com.erisco.madridshops.util.map;

import android.content.Context;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapUtil {
    public static void centerMapInPosition(GoogleMap googleMap, double latitude, double longitude) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(latitude, longitude)).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public static void addPins(List<MapPinnable> mapPinnables, final GoogleMap googleMap, final Context context) {
        if (mapPinnables == null || googleMap == null || context == null) {
            return;
        }

        for (final MapPinnable pinnable: mapPinnables) {
            final LatLng position = new LatLng(pinnable.getLatitude(), pinnable.getLongitude());
            final String profileImageUrl = pinnable.getPinImageUrl();

            final MarkerOptions marker = new MarkerOptions().position(position).title(pinnable.getPinDescription());

            Marker m = googleMap.addMarker(marker);
            m.setTag(pinnable.getRelatedModelObject());
        }
    }
}
