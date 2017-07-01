package com.erisco.madridshops.domain.interactors.invalidcache;

public interface GetInvalidCacheInteractor {
    void execute(Runnable Valid, Runnable NotValid);
}
