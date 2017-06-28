package com.erisco.madridshops.domain.managers.network.mappers;

import android.util.Log;

public class CorrectCoordinate {
    public static float getCorrectCoordinateComponent(String coordinateComponent) {
        // problem: JSON has this errors "-3.9018104,275"
        float coordinate = 0.0f;

        String s = coordinateComponent.replace(",", "");
        try {
            coordinate = Float.parseFloat(s);
        } catch (Exception e) {
            Log.d("ERROR CONVERTING", String.format("Can't convert %s", coordinateComponent));
        }
        return coordinate;
    }
}
