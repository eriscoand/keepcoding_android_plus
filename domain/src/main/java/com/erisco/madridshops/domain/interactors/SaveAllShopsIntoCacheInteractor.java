package com.erisco.madridshops.domain.interactors;

import com.erisco.madridshops.domain.model.Shop.Shops;

public interface SaveAllShopsIntoCacheInteractor {
    void execute(Shops shops, Runnable completion);
}
