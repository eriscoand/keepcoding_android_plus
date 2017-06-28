package com.erisco.madridshops.domain.interactors.cachelist;

import android.support.annotation.NonNull;

import com.erisco.madridshops.domain.interactors.list.ListInteractorCompletion;

public interface ListFromCacheInteractor {
    void execute(@NonNull final ListInteractorCompletion completion);
}
