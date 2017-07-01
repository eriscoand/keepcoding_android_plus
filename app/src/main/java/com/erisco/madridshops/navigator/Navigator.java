package com.erisco.madridshops.navigator;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.erisco.madridshops.activities.ActivityDetailActivity;
import com.erisco.madridshops.activities.ActivityListActivity;
import com.erisco.madridshops.activities.MainActivity;
import com.erisco.madridshops.activities.ShopDetailActivity;
import com.erisco.madridshops.activities.ShopListActivity;
import com.erisco.madridshops.domain.model.Activity.Activity;
import com.erisco.madridshops.domain.model.Shop.Shop;

import static com.erisco.madridshops.util.Constants.INTENT_ACTIVITY_DETAIL;
import static com.erisco.madridshops.util.Constants.INTENT_SHOP_DETAIL;

public class Navigator {
    public static Intent navigateFromMainActivityToShopListActivity(@NonNull final MainActivity mainActivity) {
        assert(mainActivity != null);

        final Intent i = new Intent(mainActivity, ShopListActivity.class);
        mainActivity.startActivity(i);

        return i;
    }

    public static Intent navigateFromMainActivityToActivityListActivity(@NonNull final MainActivity mainActivity) {
        assert(mainActivity != null);

        final Intent i = new Intent(mainActivity, ActivityListActivity.class);
        mainActivity.startActivity(i);

        return i;
    }

    public static Intent navigateFromShopListActivityToShopDetailActivity(@NonNull final ShopListActivity shopListActivity, final Shop shop, final int position) {
        final Intent i = new Intent(shopListActivity, ShopDetailActivity.class);
        i.putExtra(INTENT_SHOP_DETAIL, shop);

        shopListActivity.startActivity(i);

        return i;
    }

    public static Intent navigateFromActivityListActivityToActivityDetailActivity(@NonNull final ActivityListActivity activityListActivity, final Activity activity, final int position) {
        final Intent i = new Intent(activityListActivity, ActivityDetailActivity.class);
        i.putExtra(INTENT_ACTIVITY_DETAIL, activity);

        activityListActivity.startActivity(i);

        return i;
    }


}
