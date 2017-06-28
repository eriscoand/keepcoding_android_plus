package com.erisco.madridshops.domain.managers.network.managers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface NetworkManager {
    void getListFromServer( @NonNull final String url,
                            @NonNull final ListManagerCompletion completion,
                            @Nullable final ManagerErrorCompletion errorCompletion);
}
