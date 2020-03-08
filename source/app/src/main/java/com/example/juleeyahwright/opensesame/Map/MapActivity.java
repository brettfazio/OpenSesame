package com.example.juleeyahwright.opensesame.Map;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.juleeyahwright.opensesame.Common.BaseActivity;
import com.example.juleeyahwright.opensesame.Common.SharedPreferencesController;
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
import com.google.android.gms.maps.model.Marker;
import com.google.firebase.auth.FirebaseAuth;

/*
MapActivity: the main interface of the app, a google maps that shows the user's location,
reports can be added, and settings page can be accessed
 */
public class MapActivity extends BaseActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private MapController mapController;
    private InterfaceMapController interfaceMapController;
    private boolean selectionStateReady;

    // add the map and buttons to the main screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        // Make sure that mapFragment is not null
        assert mapFragment != null;

        mapFragment.getMapAsync(this);
        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MarkerController.displayMarkers();
                addReportClicked();
            }
        });

        mapController = new MapController(getApplicationContext());

        selectionStateReady = true;
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        setContentView(R.layout.map_activity);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        setContentView(R.layout.map_activity);
//    }

    // changes the state to allow the user to add a marker to the map
    private void addReportClicked() {
        if (!selectionStateReady) {
            selectionStateReady = true;
            changeAddState();
            return;
        }
        // selectionStateReady is false
        selectionStateReady = false;
        changeAddState();

        Toast.makeText(getApplicationContext(),
                "Select a location on the map to create a report there!",
                Toast.LENGTH_SHORT).show();
    }

    private void resetButtonState() {
        selectionStateReady = true;
        changeAddState();
    }

    // take the user to the report screen to fill out additional material, storing the location selected
    // into the report automatically
    private void intentToReport(LatLng location) {
        Intent intent = new Intent(MapActivity.this, CreateReportActivity.class);
        intent.putExtra("LOCATION", location);
        startActivity(intent);
        resetButtonState();
    }

    private void clicked(LatLng location) {
        if (!selectionStateReady) {
            intentToReport(location);
            Toast.makeText(getApplicationContext(), "new marker", Toast.LENGTH_SHORT).show();
            interfaceMapController.getMap().animateCamera(CameraUpdateFactory.newLatLng(location));
            interfaceMapController.getMap().addMarker(MarkerController.addMarker(location));
        }
    }

    // toggles between "add" and "cancel" buttons
    private void changeAddState() {
        Button addButton = findViewById(R.id.add_button);
        if (selectionStateReady) {
            addButton.setText(getResources().getString(R.string.add_button_ready));
        } else {
            addButton.setText(getResources().getString(R.string.add_button_processing));
        }
    }

    // verifies that the map exists before attempting any other api calls
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        interfaceMapController = new InterfaceMapController(googleMap, this);
        interfaceMapController.checkAndRequestPermissions(MapActivity.this, MapActivity.this);

        interfaceMapController.getMap().setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                clicked(latLng);
            }
        });

//            boolean showCompass = getIntent().getExtras().getBoolean("SHOW_COMPASS");
//            boolean satelliteMap = getIntent().getExtras().getBoolean("SATELLITE_HYBRID");
//            boolean showZoom = getIntent().getExtras().getBoolean("SHOW_ZOOM");
//
//            if(showCompass)
//               googleMap.getUiSettings().setCompassEnabled(true);
//            if(satelliteMap)
//               googleMap.setMapType(4);
//            if(showZoom)
//               googleMap.getUiSettings().setZoomControlsEnabled(true);
    }

    // adds a menu to access account
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // depending on the button selected, take the user to the appropriate screen
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.report_list_option:
                i = new Intent(MapActivity.this, ReportListActivity.class);
                startActivity(i);
                return true;
            case R.id.settings_option:
                i = new Intent(MapActivity.this, SettingsActivity.class);
                startActivity(i);
                return true;
            case R.id.sign_out_option:
                FirebaseAuth.getInstance().signOut();
                finish();
                SharedPreferencesController.clearSignInData(getApplicationContext());
                Intent intent = new Intent(MapActivity.this, LoginActivity.class);
                startActivity(intent);
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
        interfaceMapController.updateMapLocation(MapActivity.this);
    }

    // A marker was tapped on the map
    @Override
    public boolean onMarkerClick(Marker marker) {
        if (!selectionStateReady) {
            return false;
        }
        Intent intent = mapController.markerWasTapped(this, marker);
        startActivity(intent);
        return false;
    }
}
