package com.casas.fabiel.backbasecities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.casas.fabiel.backbasecities.cities.list.CityFragment;
import com.casas.fabiel.backbasecities.cities.list.CityInfo;
import com.casas.fabiel.backbasecities.cities.list.Coordinates;
import com.casas.fabiel.backbasecities.cities.map.MapsActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.casas.fabiel.backbasecities.cities.map.MapsActivity.CITY_SELECTED;

public class MainActivity extends AppCompatActivity implements MasterListInteraction.OnCityFragmentInteractionListener, OnMapReadyCallback {

    private MasterListInteraction.OnSearchListener listener;
    private GoogleMap googleMap;
    private CityInfo lastCitySelected;
    private boolean masterDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        listener = (CityFragment) getSupportFragmentManager().findFragmentById(R.id.cityList);
        if (findViewById(R.id.mapContainer) != null) {
            masterDetail = true;
            addMapInView();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(searListener());
        searchView.setOnCloseListener(searchCloseListener());
        return super.onCreateOptionsMenu(menu);
    }

    private SearchView.OnCloseListener searchCloseListener() {
        return () -> {
            listener.searchClosed();
            return false;
        };
    }

    private SearchView.OnQueryTextListener searListener() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                listener.searchItem(s);
                return false;
            }
        };
    }

    @Override
    public void onCityInteraction(CityInfo item) {
        this.lastCitySelected = item;
        if (masterDetail) {
            addMarkerInMap();
        } else {
            Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
            intent.putExtra(CITY_SELECTED, lastCitySelected);
            startActivity(intent);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, 1);
            }
        } else if (lastCitySelected != null) {
            addMarkerInMap();
        }
    }

    private void addMarkerInMap() {
        googleMap.clear();
        Coordinates coordinates = lastCitySelected.getCoord();
        LatLng latLng = new LatLng(coordinates.getRealLat(), coordinates.getRealLon());
        googleMap.addMarker(new MarkerOptions().position(latLng).title(lastCitySelected.getName()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    private void addMapInView() {
        SupportMapFragment mapFragment = new SupportMapFragment();
        mapFragment.getMapAsync(this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.mapContainer, mapFragment)
                .commit();
    }
}
