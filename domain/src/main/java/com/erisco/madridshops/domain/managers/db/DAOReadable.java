package com.erisco.madridshops.domain.managers.db;

import android.support.annotation.Nullable;

import java.util.List;

public interface DAOReadable<T> {
    T query(final long id);
    List<T> query();
    @Nullable List<T> query(String where, String[] whereArgs, String orderBy);
    int numRecords();
}
