package com.erisco.madridshops.domain.managers.cache.list;

import android.support.annotation.NonNull;

public interface ListFromCacheManager<T> {
    void execute(@NonNull final ListCacheManagerCompletion<T> completion);
}
