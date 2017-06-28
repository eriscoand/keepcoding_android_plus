package com.erisco.madridshops.domain.managers.network.mappers;

import com.erisco.madridshops.domain.managers.network.entities.JsonEntity;

import java.util.List;

/**
 * Created by erisco on 27/06/2017.
 */

public interface Mapper<T> {
    T map(final List<JsonEntity> entities);
}
