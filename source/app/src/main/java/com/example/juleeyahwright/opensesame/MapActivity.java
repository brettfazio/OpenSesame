package com.example.juleeyahwright.opensesame;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.view.MenuItem;
import android.content.Intent;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.juleeyahwright.opensesame.CreateReport.CreateReportActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String LOCATION = "location";
    private static final int ZOOM = 16;

    private GoogleMap mMap;
    private LatLng newReportLocation;
    private boolean mLocationPermissionGranted;
    private FusedLocationProviderClient fusedLocationClient;
    private Location mLastKnownLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        if(savedInstanceState != null)
        {
            mLastKnownLocation = savedInstanceState.getParcelable(LOCATION);
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final Button menuButton = (Button) findViewById(R.id.menu_button);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu mainMenu = new PopupMenu(MapActivity.this, menuButton);
                mainMenu.getMenuInflater().inflate(R.menu.main_menu, mainMenu.getMenu());
                mainMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()  {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getTitle().equals("Settings")) {
                            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                            startActivity(intent);
                        }
                        return true;
                    }

                });

                mainMenu.show();
            }
        });


        Button addButton = (Button) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addReportClicked();
            }
        });

        //TODO(): Uncomment when API is enabled
        /*mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                clicked(latLng);
            }
        });*/

    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        if(mMap != null)
        {
            outState.putParcelable(LOCATION, mLastKnownLocation);
            super.onSaveInstanceState(outState);
        }
    }

    private void addReportClicked() {
        Intent intent = new Intent(MapActivity.this, CreateReportActivity.class);
        intent.putExtra("LOCATION", newReportLocation);
        startActivity(intent);
    }

    private void clicked(LatLng location) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        checkAndRequestPermissions();
        updateMapLocation();
        getLastKnownLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            mLocationPermissionGranted = true;
        }
        updateMapLocation();
    }

    private void checkAndRequestPermissions(){
        if(ContextCompat.checkSelfPermission(
                MapActivity.this,
                ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(
                MapActivity.this,
                new String[] {ACCESS_FINE_LOCATION},
                1);
        } else {
            mLocationPermissionGranted = true;
        }
    }

    private void updateMapLocation()
    {
        if(mMap == null)
            return;

        try
        {
            if(mLocationPermissionGranted)
            {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
                getLastKnownLocation();
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                checkAndRequestPermissions();
            }
        } catch (SecurityException e){
        }
    }

    private void getLastKnownLocation()
    {
        try
        {
            if(mLocationPermissionGranted)
            {
                Task<Location> location = fusedLocationClient.getLastLocation();
                location.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if(task.isSuccessful())
                        {
                            mLastKnownLocation = task.getResult();
                            if(mLastKnownLocation != null)
                            {
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(mLastKnownLocation.getLatitude(),
                                                mLastKnownLocation.getLongitude()),
                                                ZOOM));
                            }
                        } else {
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(28.6024, -81.2001), ZOOM));
                        }
                    }
                });
            }
        } catch (SecurityException e) {
        }
    }
}