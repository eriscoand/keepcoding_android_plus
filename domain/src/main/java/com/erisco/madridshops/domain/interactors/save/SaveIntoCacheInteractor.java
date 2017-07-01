package com.erisco.madridshops.domain.interactors.save;

public interface SaveIntoCacheInteractor<T> {
    void execute(T list, Runnable completion);
}
