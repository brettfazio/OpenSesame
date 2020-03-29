package com.example.juleeyahwright.opensesame.Map;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.juleeyahwright.opensesame.AccountModel.AccountActivity;
import com.example.juleeyahwright.opensesame.Common.BaseActivity;
import com.example.juleeyahwright.opensesame.Common.SharedPreferencesController;
import com.example.juleeyahwright.opensesame.Common.UCF.UCFConstant;
import com.example.juleeyahwright.opensesame.CreateReport.CreateReportActivity;
import com.example.juleeyahwright.opensesame.LoginActivity;
import com.example.juleeyahwright.opensesame.R;
import com.example.juleeyahwright.opensesame.ReportList.ReportListActivity;
import com.example.juleeyahwright.opensesame.Settings.SettingsActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.firebase.auth.FirebaseAuth;

/*
MapActivity: the main interface of the app, a google maps that shows the user's location,
reports can be added, and settings page can be accessed
 */
public class GuestMapActivity extends BaseActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnCameraMoveListener {

    private MapController mapController;
    private MarkerController markerController;
    private InterfaceMapController interfaceMapController;
    private boolean selectionStateReady;

    // add the map and buttons to the main screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_map_activity);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        // Make sure that mapFragment is not null
        assert mapFragment != null;

        selectionStateReady = false;

        mapController = new MapController(getApplicationContext());
        markerController = new MarkerController();

        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Draw the reports
        if (interfaceMapController != null)
            interfaceMapController.drawReports();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.guest_menu, menu);
        return true;
    }



    private void clicked(LatLng location) {
        if (!selectionStateReady) {
            // intentToReport(location);
            // Toast.makeText(getApplicationContext(), "new marker", Toast.LENGTH_SHORT).show();
            interfaceMapController.getMap().animateCamera(CameraUpdateFactory.newLatLng(location));
            //TODO(brettfazio): Re-draw markers
            //interfaceMapController.getMap().addMarker(markerController.addMarker(location));
        }
    }

    // verifies that the map exists before attempting any other api calls
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        interfaceMapController = new InterfaceMapController(googleMap, this);
        interfaceMapController.checkAndRequestPermissions(GuestMapActivity.this, GuestMapActivity.this);

        interfaceMapController.getMap().setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                clicked(latLng);
            }
        });
        interfaceMapController.getMap().setOnMarkerClickListener(this);
        interfaceMapController.getMap().setOnCameraMoveListener(this);
        interfaceMapController.drawReports();
        if(SharedPreferencesController.getMapType(getApplicationContext()) == "hybrid")
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        else if (SharedPreferencesController.getMapTheme(getApplicationContext()) != null) {
            setMapTheme(googleMap);
        }

        // Set up work order button.
        this.onCameraMove();

    }

    public void setMapTheme(GoogleMap googleMap) {
        boolean success = false;
        try {
            switch (SharedPreferencesController.getMapTheme(getApplicationContext())) {
                case ("ucf"):
                    success = googleMap.setMapStyle(
                       MapStyleOptions.loadRawResourceStyle(
                               this, R.raw.ucf_map_style));
                    break;
                case("night"):
                    success = googleMap.setMapStyle(
                       MapStyleOptions.loadRawResourceStyle(
                               this, R.raw.night_style));
                    break;
                case("monochrome"):
                    success = googleMap.setMapStyle(
                       MapStyleOptions.loadRawResourceStyle(
                               this, R.raw.monochrome_style));
                    break;
                case("dark_blue"):
                    success = googleMap.setMapStyle(
                       MapStyleOptions.loadRawResourceStyle(
                               this, R.raw.dark_blue_style));
                    break;
                default:
                    success = googleMap.setMapStyle(
                       MapStyleOptions.loadRawResourceStyle(
                               this, R.raw.standard_theme));
                    break;
            }

            if(!success) {
                Log.i("styles", "Either parsing failed or default is selected.");
            }

        } catch (Resources.NotFoundException e) {

        }
    }


    // depending on the button selected, take the user to the appropriate screen
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.report_list_option:
                i = new Intent(GuestMapActivity.this, ReportListActivity.class);
                startActivity(i);
                return true;
            case R.id.settings_option:
                i = new Intent(GuestMapActivity.this, SettingsActivity.class);
                startActivity(i);
                return true;
            case R.id.sign_in_option:
                i = new Intent(GuestMapActivity.this, LoginActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // if permission for location services is granted, the user's location will be displayed on the map
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        interfaceMapController.setLocationPermission(false);
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            interfaceMapController.setLocationPermission(true);
        }
        interfaceMapController.updateMapLocation(GuestMapActivity.this);
    }

    // A marker was tapped on the map
    @Override
    public boolean onMarkerClick(Marker marker) {
        System.out.println("Marker was tapped!!!!" + selectionStateReady);
        if (!selectionStateReady) {
            return false;
        }
        Intent intent = mapController.markerWasTapped(this, marker);
        startActivity(intent);
        return false;
    }

    @Override
    public void onCameraMove() {
        LatLng center = interfaceMapController.getMap().getCameraPosition().target;
    }
}
