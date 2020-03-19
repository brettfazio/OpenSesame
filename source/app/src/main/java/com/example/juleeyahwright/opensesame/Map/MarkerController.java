package com.example.juleeyahwright.opensesame.Map;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.google.android.gms.maps.model.MarkerOptions;

public class MarkerController extends AppCompatActivity {
    public static MarkerOptions createMarker(ReportReference reportReference) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(reportReference.getLocation());
        markerOptions.title(reportReference.getName());
        return markerOptions;
    }
}
