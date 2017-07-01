package com.erisco.madridshops.views;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.erisco.madridshops.R;
import com.erisco.madridshops.domain.model.Activity.Activity;
import com.erisco.madridshops.util.map.PinCallback;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class ActivityPinAdapter implements GoogleMap.InfoWindowAdapter{

    private final View infoView;
    private LayoutInflater mapInflater;

    public ActivityPinAdapter(LayoutInflater inflater) {
        mapInflater = inflater;
        infoView = mapInflater.inflate(R.layout.pin_element, null);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {

        Activity activity = (Activity) marker.getTag();

        final ImageView logo = (ImageView) infoView.findViewById(R.id.pin_element__logo);
        final TextView title = (TextView) infoView.findViewById(R.id.pin_element__title);

        title.setText(activity.getName());
        Picasso.with(mapInflater.getContext())
                .load(activity.getLogoUrl())
                .into(logo, new PinCallback(marker));

        return infoView;
    }

}
