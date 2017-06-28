package com.erisco.madridshops.domain.interactors.list;

import android.support.annotation.NonNull;

public interface ListInteractorCompletion<T> {
    void completion(@NonNull final T list);
}
