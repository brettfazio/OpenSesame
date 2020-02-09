package com.example.juleeyahwright.opensesame;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.view.MenuItem;
import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.juleeyahwright.opensesame.CreateReport.CreateReportActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng newReportLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);
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

    private void addReportClicked() {
        Intent intent = new Intent(MapActivity.this, CreateReportActivity.class);
        intent.putExtra("LOCATION", newReportLocation);
        startActivity(intent);
    }

    private void clicked(LatLng location) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        checkAndRequestPermissions();

        mMap = googleMap;
        LatLng ucf = new LatLng(28.6024, -81.2001);
        mMap.addMarker(new MarkerOptions().position(ucf).title("Welcome to UCF!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ucf, 16.0f));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                
            } else {

            }
            return;
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
        }
    }
}