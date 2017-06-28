package com.erisco.madridshops.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.erisco.madridshops.R;
import com.erisco.madridshops.domain.interactors.cachelist.ListFromCacheInteractor;
import com.erisco.madridshops.domain.interactors.cachelist.ShopsListFromCacheInteractorImpl;
import com.erisco.madridshops.domain.interactors.list.ListInteractor;
import com.erisco.madridshops.domain.interactors.list.ListInteractorCompletion;
import com.erisco.madridshops.domain.interactors.list.ShopsListInteractorImpl;
import com.erisco.madridshops.domain.interactors.cache.GetCachedInteractor;
import com.erisco.madridshops.domain.interactors.cache.get.ShopsGetCachedInteractorImpl;
import com.erisco.madridshops.domain.interactors.InteractorErrorCompletion;
import com.erisco.madridshops.domain.interactors.SaveAllShopsIntoCacheInteractor;
import com.erisco.madridshops.domain.interactors.SaveAllShopsIntoCacheInteractorImpl;
import com.erisco.madridshops.domain.interactors.cache.SetCachedInteractor;
import com.erisco.madridshops.domain.interactors.cache.set.ShopsSetCachedInteractorImpl;
import com.erisco.madridshops.domain.managers.cache.list.ListFromCacheManager;
import com.erisco.madridshops.domain.managers.cache.list.ShopListFromCacheManagerDAOImpl;
import com.erisco.madridshops.domain.managers.cache.save.SaveListIntoCacheManager;
import com.erisco.madridshops.domain.managers.cache.save.ShopsSaveListIntoCacheManagerDAOImpl;
import com.erisco.madridshops.domain.managers.network.managers.ListManagerImplementation;
import com.erisco.madridshops.domain.managers.network.managers.NetworkManager;
import com.erisco.madridshops.domain.model.Shop.Shop;
import com.erisco.madridshops.domain.model.Shop.Shops;
import com.erisco.madridshops.fragments.ShopsFragment;
import com.erisco.madridshops.navigator.Navigator;
import com.erisco.madridshops.util.map.MapPinnable;
import com.erisco.madridshops.util.map.MapUtil;
import com.erisco.madridshops.util.map.model.ShopPin;
import com.erisco.madridshops.views.OnElementClick;

import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL;
import static com.erisco.madridshops.util.map.MapUtil.centerMapInPosition;

public class ShopListActivity extends AppCompatActivity {

    @BindView(R.id.activity_shop_list__progress_bar) ProgressBar progressBar;

    ShopsFragment shopsFragment;
    private SupportMapFragment mapFragment;
    public GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        ButterKnife.bind(this);

        shopsFragment = (ShopsFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shop_list__fragment_shops);

        initializeMap();

    }

    private void checkCacheData() {
        GetCachedInteractor getIfAllShopsAreCachedInteractor = new ShopsGetCachedInteractorImpl(this);
        getIfAllShopsAreCachedInteractor.execute(new Runnable() {
            @Override
            public void run() {
                // all cached already, no need to download things, just read from DB
                readDataFromCache();

            }
        }, new Runnable() {
            @Override
            public void run() {
                // nothing cached yet
                obtainShopsList();
            }
        });
    }

    private void initializeMap() {
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shop_list__map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                // check if map is created successfully or not
                if (googleMap == null) {
                    Toast.makeText(getApplicationContext(),
                            "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    map = googleMap;

                    checkCacheData();

                    addDataToMap(googleMap);
                }
            }
        });
    }

    public void addDataToMap(GoogleMap map) {
        if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }


        centerMapInPosition(map, 40.411335, -3.674908);
        map.setBuildingsEnabled(true);
        map.setMapType(MAP_TYPE_NORMAL);
        map.getUiSettings().setRotateGesturesEnabled(false);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.setMyLocationEnabled(true);

        MarkerOptions retiroMarkerOptions = new MarkerOptions()
                .position(new LatLng(40.411335, -3.674908))
                .title("Hello world").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

        MarkerOptions retiroMarkerOptions2 = new MarkerOptions()
                .position(new LatLng(40.42, -3.674908))
                .title("Hello world 2").icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_camera));

        Marker marker = map.addMarker(retiroMarkerOptions);
        map.addMarker(retiroMarkerOptions2);

    }


    private void readDataFromCache() {
        ListFromCacheManager getAllShopsFromCacheManager = new ShopListFromCacheManagerDAOImpl(this);
        ListFromCacheInteractor getAllShopsFromCacheInteractor = new ShopsListFromCacheInteractorImpl(getAllShopsFromCacheManager);
        getAllShopsFromCacheInteractor.execute(new ListInteractorCompletion<Shops>() {
            @Override
            public void completion(@NonNull Shops shops) {
                configShopsFragment(shops);
            }
        });
    }

    private void obtainShopsList() {
        progressBar.setVisibility(View.VISIBLE);

        NetworkManager manager = new ListManagerImplementation(this);
        ListInteractor getAllShopsInteractor = new ShopsListInteractorImpl(getString(R.string.JSON_ACTIVITIES_URL), manager);
        getAllShopsInteractor.execute(
            new ListInteractorCompletion<Shops>() {
                @Override
                public void completion(Shops shops) {
                    SaveListIntoCacheManager saveManager = new ShopsSaveListIntoCacheManagerDAOImpl(getBaseContext());
                    SaveAllShopsIntoCacheInteractor saveInteractor = new SaveAllShopsIntoCacheInteractorImpl(saveManager);
                    saveInteractor.execute(shops, new Runnable() {
                        @Override
                        public void run() {
                            SetCachedInteractor setAllShopsCachedInteractor = new ShopsSetCachedInteractorImpl(getBaseContext());
                            setAllShopsCachedInteractor.execute(true);
                        }
                    });

                    configShopsFragment(shops);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            },
            new InteractorErrorCompletion() {
                @Override
                public void onError(String errorDescription) {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        );
    }

    private void configShopsFragment(final Shops shops) {
        shopsFragment.setShops(shops);
        shopsFragment.setOnElementClickListener(new OnElementClick<Shop>() {
            @Override
            public void clickedOn(@NonNull Shop element, int position) {
                // TODO: finish
                Navigator.navigateFromShopListActivityToShopDetailActivity(ShopListActivity.this, element, position);
            }
        });

        putShopPinsOnMap(shops);
    }

    private void putShopPinsOnMap(Shops shops) {
        List<MapPinnable> shopPins = ShopPin.shopPinsFromShops(shops);
        MapUtil.addPins(shopPins, map, this);

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if (marker.getTag() == null || !(marker.getTag() instanceof Shop)) {
                    return;
                }
                Shop shop = (Shop) marker.getTag();
                Navigator.navigateFromShopListActivityToShopDetailActivity(ShopListActivity.this, shop, 0);
            }
        });
    }
}
