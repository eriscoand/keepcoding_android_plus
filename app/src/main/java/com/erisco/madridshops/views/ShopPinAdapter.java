package com.erisco.madridshops.views;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.erisco.madridshops.R;
import com.erisco.madridshops.domain.model.Shop.Shop;
import com.erisco.madridshops.util.map.PinCallback;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class ShopPinAdapter implements GoogleMap.InfoWindowAdapter{

    private final View infoView;
    private LayoutInflater mapInflater;

    public ShopPinAdapter(LayoutInflater inflater) {
        mapInflater = inflater;
        infoView = mapInflater.inflate(R.layout.pin_element, null);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {

        Shop shop = (Shop) marker.getTag();

        final ImageView logo = (ImageView) infoView.findViewById(R.id.pin_element__logo);
        final TextView title = (TextView) infoView.findViewById(R.id.pin_element__title);

        title.setText(shop.getName());
        Picasso.with(mapInflater.getContext())
                .load(shop.getLogoUrl())
                .into(logo, new PinCallback(marker));

        return infoView;
    }

}
