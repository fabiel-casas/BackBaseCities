package com.casas.fabiel.backbasecities.cities.map;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.casas.fabiel.backbasecities.R;
import com.casas.fabiel.backbasecities.cities.list.CityInfo;
import com.casas.fabiel.backbasecities.cities.list.Coordinates;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.Manifest;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final String CITY_SELECTED = "CitySelected";
    private GoogleMap googleMap;
    private CityInfo city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        city = getIntent().getExtras().getParcelable(CITY_SELECTED);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (googleMap != null) {
            addMarkerInMap();
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
            } else {
                addMarkerInMap();
            }
        } else {
            addMarkerInMap();
        }
    }

    private void addMarkerInMap() {
        googleMap.clear();
        Coordinates coordinates = city.getCoord();
        LatLng latLng = new LatLng(coordinates.getRealLat(), coordinates.getRealLon());
        googleMap.addMarker(new MarkerOptions().position(latLng).title(city.getName()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}
