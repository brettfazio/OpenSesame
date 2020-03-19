package com.example.juleeyahwright.opensesame.Map;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.juleeyahwright.opensesame.Report.Get.ReportGetService;
import com.example.juleeyahwright.opensesame.Report.Get.ReportGetServiceListener;
import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

/*
MapController: gets the interaction between the user and the locations on the map
 */
public class InterfaceMapController implements ReportGetServiceListener {

    private static final int ZOOM = 16;

    private final GoogleMap mMap;
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
    public InterfaceMapController(GoogleMap mMap, Activity a) {
        this.mLocationPermissionGranted = false;
        this.mMap = mMap;
        try {
            this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(a);
        } catch (NullPointerException ignored) {
        }
    }

    public void setLocationPermission(boolean b) {
        this.mLocationPermissionGranted = b;
    }

    public boolean getLocationPermission() {
        return this.mLocationPermissionGranted;
    }

    public void checkAndRequestPermissions(Context c, Activity a) {
        if (ContextCompat.checkSelfPermission(
                c,
                ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    a,
                    new String[]{ACCESS_FINE_LOCATION},
                    1);
        } else {
            this.mLocationPermissionGranted = true;
            updateMapLocation(a);
        }
    }

    void updateMapLocation(Activity a) {
        if (mMap == null)
            return;

        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
                getLastKnownLocation(a);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
            }
        } catch (SecurityException e) {
        }
    }

    private void getLastKnownLocation(Activity a) {
        try {
            if (mLocationPermissionGranted) {
                Task<Location> location = fusedLocationClient.getLastLocation();
                location.addOnCompleteListener(a, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            mLastKnownLocation = task.getResult();
                            if (mLastKnownLocation != null) {
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

    public void drawReports() {
        ReportGetService reportGetService = new ReportGetService(this);
        reportGetService.getReports();

    }

    @Override
    public void reportRetrievalSuccess(@NotNull QuerySnapshot querySnapshot, @NotNull ReportReference[] reportReferences) {
        for (ReportReference reportReference : reportReferences) {
            Marker marker = this.mMap.addMarker(MarkerController.createMarker(reportReference));
            marker.setTag(reportReference);
        }
    }

    @Override
    public void reportRetrievalFailure(@NotNull Exception exception) {

    }
}
