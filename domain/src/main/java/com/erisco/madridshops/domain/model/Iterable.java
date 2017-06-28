package com.erisco.madridshops.domain.model;

import java.util.List;

public interface Iterable<T> {
    long size();
    T get(long index);
    List<T> allElements();
}
