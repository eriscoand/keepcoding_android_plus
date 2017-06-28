package com.erisco.madridshops.domain.managers.cache.clear;

public interface ClearCacheManager {
    void execute(final Runnable completion);
}
