package com.erisco.madridshops.domain.model;

public interface Updatable<T> {
    void add(T element);
    void delete(T element);
    void update(T element, long index);
}
