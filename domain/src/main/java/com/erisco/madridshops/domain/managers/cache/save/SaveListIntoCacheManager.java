package com.erisco.madridshops.domain.managers.cache.save;

public interface SaveListIntoCacheManager<T> {
    void execute(T list, Runnable completion);
}
