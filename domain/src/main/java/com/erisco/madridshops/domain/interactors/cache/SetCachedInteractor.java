package com.erisco.madridshops.domain.interactors.cache;

public interface SetCachedInteractor {
    public static final String SHOPS_SAVED = "SHOPS_SAVED";
    public static final String ACTIVITIES_SAVED = "ACTIVITIES_SAVED";

    void execute(boolean saved);
}
