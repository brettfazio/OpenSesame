package com.example.juleeyahwright.opensesame.Map;

import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.example.juleeyahwright.opensesame.Settings.SettingsActivity;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;

public class MarkerController extends AppCompatActivity {
    public static MarkerOptions createMarker(ReportReference reportReference) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(reportReference.getLocation());
        markerOptions.title(reportReference.getName());
        markerOptions.icon(getMarkerIcon(SettingsActivity.getColor()));
        return markerOptions;
    }

    public static BitmapDescriptor getMarkerIcon(String color) {
        float[] hsv = new float[3];
        Color.colorToHSV(Color.parseColor(color), hsv);
        return BitmapDescriptorFactory.defaultMarker(hsv[0]);
    }
}
