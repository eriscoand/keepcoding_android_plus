package com.erisco.madridshops.util.map;

public interface MapPinnable<E> {
    float getLatitude();
    float getLongitude();
    String getPinDescription();
    String getPinImageUrl();
    E getRelatedModelObject();
}