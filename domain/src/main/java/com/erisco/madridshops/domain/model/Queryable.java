package com.erisco.madridshops.domain.model;

import java.util.List;

public interface Queryable<T> {
    List<T> query(String query);
}
