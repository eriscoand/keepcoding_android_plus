package com.erisco.madridshops.domain.interactors.invalidcache;

public interface SetInvalidCacheInteractor {
    public static final String CACHE_INVALID_INDICATOR = "VALID_CACHE";

    void execute();
}
