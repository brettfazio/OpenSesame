package com.example.juleeyahwright.opensesame.test;

import com.example.juleeyahwright.opensesame.Map.MarkerController;
import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.junit.Assert;
import org.junit.Test;

import static com.example.juleeyahwright.opensesame.Map.MarkerController.createMarker;

public class MarkerControllerTest {

    private MarkerController markerController;

    @Test
    public void addMarkerTest() {
        markerController = new MarkerController();
        LatLng latLng = new LatLng(28.6024274, -81.2000599);
        ReportReference reportReference = new ReportReference("report",
                "this is a report",
                "basement",
                latLng,
                "eFz687FFHDHD",
                "hashUID:");
        MarkerOptions markerOptions = createMarker(reportReference);
        Assert.assertEquals(latLng, markerOptions.getPosition());
        assert (markerOptions.getTitle().equals(reportReference.getName()));
    }
}