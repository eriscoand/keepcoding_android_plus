package com.erisco.madridshops.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.erisco.madridshops.R;
import com.erisco.madridshops.domain.interactors.InteractorErrorCompletion;
import com.erisco.madridshops.domain.interactors.cache.GetCachedInteractor;
import com.erisco.madridshops.domain.interactors.cache.SetCachedInteractor;
import com.erisco.madridshops.domain.interactors.cache.get.ActivitiesGetCachedInteractorImpl;
import com.erisco.madridshops.domain.interactors.cache.set.ActivitiesSetCachedInteractorImpl;
import com.erisco.madridshops.domain.interactors.cachelist.ListFromCacheInteractor;
import com.erisco.madridshops.domain.interactors.cachelist.ActivitiesListFromCacheInteractorImpl;
import com.erisco.madridshops.domain.interactors.invalidcache.SetInvalidCacheInteractor;
import com.erisco.madridshops.domain.interactors.invalidcache.set.SetInvalidCacheInteractorImpDate;
import com.erisco.madridshops.domain.interactors.list.ListInteractor;
import com.erisco.madridshops.domain.interactors.list.ListInteractorCompletion;
import com.erisco.madridshops.domain.interactors.list.ActivitiesListInteractorImpl;
import com.erisco.madridshops.domain.interactors.save.ActivitiesSaveIntoCacheInteractorImpl;
import com.erisco.madridshops.domain.interactors.save.SaveIntoCacheInteractor;
import com.erisco.madridshops.domain.managers.cache.list.ListFromCacheManager;
import com.erisco.madridshops.domain.managers.cache.list.ActivityListFromCacheManagerDAOImpl;
import com.erisco.madridshops.domain.managers.cache.save.SaveListIntoCacheManager;
import com.erisco.madridshops.domain.managers.cache.save.ActivitiesSaveListIntoCacheManagerDAOImpl;
import com.erisco.madridshops.domain.managers.network.managers.ListManagerImplementation;
import com.erisco.madridshops.domain.managers.network.managers.NetworkManager;
import com.erisco.madridshops.domain.model.Activity.Activities;
import com.erisco.madridshops.domain.model.Activity.Activity;
import com.erisco.madridshops.fragments.ActivitiesFragment;
import com.erisco.madridshops.navigator.Navigator;
import com.erisco.madridshops.util.map.MapPinnable;
import com.erisco.madridshops.util.map.MapUtil;
import com.erisco.madridshops.util.map.model.ActivityPin;
import com.erisco.madridshops.views.OnElementClick;
import com.erisco.madridshops.views.ActivityPinAdapter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.erisco.madridshops.util.map.MapUtil.centerMapInPosition;

public class ActivityListActivity extends AppCompatActivity {

    @BindView(R.id.activity_activity_list__progress_bar) ProgressBar progressBar;

    ActivitiesFragment activitiesFragment;
    private SupportMapFragment mapFragment;
    public GoogleMap map;

    private Activities fullActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_list);
        ButterKnife.bind(this);

        activitiesFragment = (ActivitiesFragment) getSupportFragmentManager().findFragmentById(R.id.activity_activity_list__fragment_activities);

        initializeMap();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);

        final SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                configActivitiesFragment(fullActivities);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                List<Activity> queryActivities = fullActivities.query(query);
                Activities activities = new Activities().from(queryActivities);
                configActivitiesFragment(activities);
                return false;
            }
        });

        return true;
    }


    private void checkCacheData() {
        GetCachedInteractor getIfAllActivitiesAreCachedInteractor = new ActivitiesGetCachedInteractorImpl(this);
        getIfAllActivitiesAreCachedInteractor.execute(new Runnable() {
            @Override
            public void run() {
                // all cached already, no need to download things, just read from DB
                readDataFromCache();

            }
        }, new Runnable() {
            @Override
            public void run() {
                // nothing cached yet
                obtainActivitiesList();
            }
        });
    }

    private void initializeMap() {
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.activity_activity_list__map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                // check if map is created successfully or not
                if (googleMap == null) {
                    Toast.makeText(getApplicationContext(),
                            R.string.unable_create_maps, Toast.LENGTH_SHORT)
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
            return;
        }

        centerMapInPosition(map, getString(R.string.default_lat), getString(R.string.default_lng), getString(R.string.default_zoom));
        this.map = MapUtil.init(map);

    }


    private void readDataFromCache() {
        ListFromCacheManager getAllActivitiesFromCacheManager = new ActivityListFromCacheManagerDAOImpl(this);
        ListFromCacheInteractor getAllActivitiesFromCacheInteractor = new ActivitiesListFromCacheInteractorImpl(getAllActivitiesFromCacheManager);
        getAllActivitiesFromCacheInteractor.execute(new ListInteractorCompletion<Activities>() {
            @Override
            public void completion(@NonNull Activities activities) {
                configActivitiesFragment(activities);
                fullActivities = activities;
            }
        });
    }

    private void obtainActivitiesList() {
        progressBar.setVisibility(View.VISIBLE);

        NetworkManager manager = new ListManagerImplementation(this);
        ListInteractor getAllActivitiesInteractor = new ActivitiesListInteractorImpl(getString(R.string.JSON_ACTIVITIES_URL), manager);
        getAllActivitiesInteractor.execute(
            new ListInteractorCompletion<Activities>() {
                @Override
                public void completion(Activities activities) {
                    SaveListIntoCacheManager saveManager = new ActivitiesSaveListIntoCacheManagerDAOImpl(getBaseContext());
                    SaveIntoCacheInteractor saveInteractor = new ActivitiesSaveIntoCacheInteractorImpl(saveManager);
                    saveInteractor.execute(activities, new Runnable() {
                        @Override
                        public void run() {
                            SetCachedInteractor setAllActivitiesCachedInteractor = new ActivitiesSetCachedInteractorImpl(getBaseContext());
                            setAllActivitiesCachedInteractor.execute(true);

                            SetInvalidCacheInteractor setInvalidCache = new SetInvalidCacheInteractorImpDate(getBaseContext());
                            setInvalidCache.execute();
                        }
                    });

                    configActivitiesFragment(activities);
                    fullActivities = activities;
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

    private void configActivitiesFragment(final Activities activities) {
        activitiesFragment.setActivities(activities);
        activitiesFragment.setOnElementClickListener(new OnElementClick<Activity>() {
            @Override
            public void clickedOn(@NonNull Activity activity, int position) {
                Navigator.navigateFromActivityListActivityToActivityDetailActivity(ActivityListActivity.this, activity, position);
            }
        });

        putActivityPinsOnMap(activities);
    }

    private void putActivityPinsOnMap(Activities activities) {
        List<MapPinnable> activityPins = ActivityPin.activityPinsFromActivities(activities);
        MapUtil.addPins(activityPins, map, this);

        map.setInfoWindowAdapter(new ActivityPinAdapter(getLayoutInflater()));

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if (marker.getTag() == null || !(marker.getTag() instanceof Activity)) {
                    return;
                }
                Activity activity = (Activity) marker.getTag();
                Navigator.navigateFromActivityListActivityToActivityDetailActivity(ActivityListActivity.this, activity, 0);
            }
        });
    }
}
