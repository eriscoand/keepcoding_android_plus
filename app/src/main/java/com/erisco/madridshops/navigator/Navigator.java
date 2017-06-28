package com.erisco.madridshops.navigator;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.erisco.madridshops.activities.MainActivity;
import com.erisco.madridshops.activities.ShopDetailActivity;
import com.erisco.madridshops.activities.ShopListActivity;
import com.erisco.madridshops.domain.model.Shop.Shop;

import static com.erisco.madridshops.util.Constants.INTENT_SHOP_DETAIL;

public class Navigator {
    public static Intent navigateFromMainActivityToShopListActivity(@NonNull final MainActivity mainActivity) {
        assert(mainActivity != null);

        final Intent i = new Intent(mainActivity, ShopListActivity.class);
        mainActivity.startActivity(i);

        return i;
    }

    public static Intent navigateFromShopListActivityToShopDetailActivity(@NonNull final ShopListActivity shopListActivity, final Shop shop, final int position) {
        final Intent i = new Intent(shopListActivity, ShopDetailActivity.class);
        i.putExtra(INTENT_SHOP_DETAIL, shop);

        shopListActivity.startActivity(i);

        return i;
    }


}
