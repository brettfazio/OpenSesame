package com.example.juleeyahwright.opensesame.Map;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MarkerController extends AppCompatActivity {

    public static MarkerOptions addMarker(LatLng latLng) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(latLng.latitude + " : " + latLng.longitude);
        return markerOptions;
    }

    /* Display all markers on map...still figuring out how to use firebasedatabase */
    public static void displayMarkers(){
        //Log.i("myTag", FirebaseDatabase.getInstance().getReference("name").toString());
    }

    public static void removeMarker(){

    }
}
