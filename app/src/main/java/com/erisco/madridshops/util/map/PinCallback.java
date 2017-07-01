package com.erisco.madridshops.util.map;

import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Callback;

public class PinCallback implements Callback {

    Marker pin;

    public PinCallback(Marker pin) {
        this.pin = pin;
    }

    @Override
    public void onSuccess() {
        if(pin != null && pin.isInfoWindowShown()){
            //Reload info window to show image :(
            pin.hideInfoWindow();
            pin.showInfoWindow();
        }
    }

    @Override
    public void onError() {
        //TODO: Implement some error return?
    }

}
