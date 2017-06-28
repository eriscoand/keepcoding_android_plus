package com.erisco.madridshops.domain;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import com.erisco.madridshops.domain.managers.db.ShopDAO.ShopDAO;
import com.erisco.madridshops.domain.model.Shop.Shop;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ShopDAOTests {
    final static Context appContext = InstrumentationRegistry.getTargetContext();
    public static final int TEST_ID = 888;
    public static final String TEST_NAME = "name";

    @Test
    public void given_shop_DAO_inserts_shop() throws Exception {
        ShopDAO sut = new ShopDAO(appContext);
        Shop shop = Shop.of(1, "Shop test").
                        setAddress("address").
                        setLatitude(10).
                        setLongitude(11);

        long id = sut.insert(shop);
        assertTrue(id > 0);

    }

    @Test
    public void given_inserted_shops_DAO_queries_all_shops() throws Exception {
        ShopDAO sut = new ShopDAO(appContext);
        Shop shop = insertShop(sut, 1, "Shop test", "address", 10, 11);

        List<Shop> shops = sut.query();

        assertNotNull(shops);
        assertTrue(shops.size() >= 1);
    }

    @Test
    public void given_inserted_shops_deleteall_returns_empty_table() throws Exception {
        ShopDAO sut = new ShopDAO(appContext);

        insertShops();

        sut.deleteAll();

        List<Shop> shopList = sut.query();

        assertNull(shopList);
    }

    @Test
    public void given_one_inserted_shop_I_can_delete_that_shop() throws Exception {
        ShopDAO sut = new ShopDAO(appContext);

        sut.deleteAll();
        Shop insertedShop = insertShop(sut, TEST_ID, TEST_NAME, "", 10, 10);
        Shop shop = sut.query(insertedShop.getId());

        assertNotNull(shop);
        assertEquals(insertedShop.getId(), shop.getId());
        assertEquals(TEST_NAME, shop.getName());
    }


    private Shop insertShop(ShopDAO sut, long id, String name, String address, float latitude, float longitude) {
        Shop shop = Shop.of(id, name).
                setAddress(address).
                setLatitude(latitude).
                setLongitude(latitude);

        long insertedId = sut.insert(shop);
        return shop;
    }

    private void insertShops() {
        // Context appContext = InstrumentationRegistry.getTargetContext();
        ShopDAO sut = new ShopDAO(appContext);
        for (int i = 0; i < 10; i++) {
            insertShop(sut, i, "Shop " + i, "Address " + i, i+1, i);
        }
    }
}
