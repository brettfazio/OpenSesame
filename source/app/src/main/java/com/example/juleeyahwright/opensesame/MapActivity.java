package com.example.juleeyahwright.opensesame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.view.MenuItem;
import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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
        Intent intent = new Intent(getApplicationContext(), CreateReportActivity.class);
        intent.putExtra("LOCATION", newReportLocation);
        startActivity(intent);
    }

    private void clicked(LatLng location) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng ucf = new LatLng(28.6024, -81.2001);
        mMap.addMarker(new MarkerOptions().position(ucf).title("Welcome to UCF!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ucf, 16.0f));
    }

}