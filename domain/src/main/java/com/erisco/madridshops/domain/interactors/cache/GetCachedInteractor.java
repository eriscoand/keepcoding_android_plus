package com.erisco.madridshops.domain.interactors.cache;

public interface GetCachedInteractor {
    void execute(Runnable Cached, Runnable NotCached);
}
