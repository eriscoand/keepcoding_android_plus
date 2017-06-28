package com.erisco.madridshops.domain.interactors.list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.erisco.madridshops.domain.interactors.InteractorErrorCompletion;

public interface ListInteractor {
    public void execute(@NonNull final ListInteractorCompletion completion,
                        @Nullable final InteractorErrorCompletion onError);
}
