package com.erisco.madridshops.domain.interactors.list.fake;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.erisco.madridshops.domain.interactors.InteractorErrorCompletion;
import com.erisco.madridshops.domain.interactors.list.ListInteractorCompletion;
import com.erisco.madridshops.domain.interactors.list.ListInteractor;
import com.erisco.madridshops.domain.model.Shop.Shop;
import com.erisco.madridshops.domain.model.Shop.Shops;

public class ShopsListInteractorFakeImpl implements ListInteractor {

    @Override
    public void execute(@NonNull ListInteractorCompletion completion, @Nullable InteractorErrorCompletion onError) {
        Shops shops = new Shops();

        for (int i = 0; i < 10; i++) {
            Shop shop = Shop.of(i, "My shop " + i).
                    setLogoUrl("http://icons.iconarchive.com/icons/custom-icon-design/pretty-office-11/512/shop-icon.png");
            shops.add(shop);
        }

        if (completion != null) {
            completion.completion(shops);
        }
    }
}
