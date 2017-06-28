package com.erisco.madridshops.domain.managers.cache.list;

import android.support.annotation.NonNull;

import com.erisco.madridshops.domain.model.Shop.Shops;

public interface ListCacheManagerCompletion<T> {
    void completion(@NonNull final T list);
}
