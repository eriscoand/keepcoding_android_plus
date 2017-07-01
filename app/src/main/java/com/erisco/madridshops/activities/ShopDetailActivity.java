package com.erisco.madridshops.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.erisco.madridshops.R;
import com.erisco.madridshops.domain.model.Shop.Shop;
import com.erisco.madridshops.util.Constants;
import com.erisco.madridshops.util.StaticMapImage;

public class ShopDetailActivity extends AppCompatActivity {
    @BindView(R.id.activity_shop_detail__shop_address) TextView address;
    @BindView(R.id.activity_shop_detail__shop_description) TextView description;
    @BindView(R.id.activity_shop_detail__shop_image) ImageView shopImage;
    @BindView(R.id.activity_shop_detail__shop_map) ImageView mapImage;
    @BindView(R.id.activity_shop_detail__shop_name) TextView name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null) {
            Shop shop = (Shop) intent.getSerializableExtra(Constants.INTENT_SHOP_DETAIL);
            name.setText(shop.getName());
            address.setText(shop.getAddress());
            description.setText(shop.getDescription());
            Picasso.with(this).
                    load(shop.getImageUrl()).
                    placeholder(R.drawable.shop_placeholder).
                    into(shopImage);

            String staticMapUrl = StaticMapImage.getMapImageUrl(shop.getLatitude(),shop.getLongitude());
            Picasso.with(this).
                    load(staticMapUrl).
                    placeholder(R.drawable.map_placeholder).
                    into(mapImage);
        }
    }
}
