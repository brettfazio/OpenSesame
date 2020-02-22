package com.example.juleeyahwright.opensesame.Map;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
/*
MapController: gets the interaction between the user and the locations on the map
 */
public class MapController {

    private static final int ZOOM = 16;

    private GoogleMap mMap;
    private boolean mLocationPermissionGranted;
    private FusedLocationProviderClient fusedLocationClient;
    private Location mLastKnownLocation;

    public void setLocation(Location l) {
        this.mLastKnownLocation = l;
    }

    public Location getLocation() {
        return mLastKnownLocation;
    }

    public GoogleMap getMap() {
        return mMap;
    }

    // map sets location permissions to false by default
    public MapController(GoogleMap mMap, Activity a) {
        this.mLocationPermissionGranted = false;
        this.mMap = mMap;
        try {
            this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(a);
        } catch (NullPointerException n) {
        }
    }

    public void setLocationPermission(boolean b) {
        this.mLocationPermissionGranted = b;
    }

    public boolean getLocationPermission() {
        return this.mLocationPermissionGranted;
    }

    public void checkAndRequestPermissions(Context c, Activity a){
        if(ContextCompat.checkSelfPermission(
                c,
                ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(
                    a,
                    new String[] {ACCESS_FINE_LOCATION},
                    1);
        } else {
            this.mLocationPermissionGranted = true;
            updateMapLocation(a);
        }
    }

    public void updateMapLocation(Activity a)
    {
        if(mMap == null)
            return;

        try
        {
            if(mLocationPermissionGranted)
            {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
                getLastKnownLocation(a);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
            }
        } catch (SecurityException e){
        }
    }

    public void getLastKnownLocation(Activity a)
    {
        try
        {
            if(mLocationPermissionGranted)
            {
                Task<Location> location = fusedLocationClient.getLastLocation();
                location.addOnCompleteListener(a, new OnCompleteListener<Location>() {
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
