package com.erisco.madridshops.domain.managers.network.managers;

import android.support.annotation.NonNull;

import java.util.List;

import com.erisco.madridshops.domain.managers.network.entities.JsonEntity;

public interface ListManagerCompletion {
    void completion(@NonNull final List<JsonEntity> jsonEntities);
}
