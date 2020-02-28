package com.example.juleeyahwright.opensesame.test;

import com.example.juleeyahwright.opensesame.Map.MarkerController;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.junit.Assert;
import org.junit.Test;

import static com.example.juleeyahwright.opensesame.Map.MarkerController.addMarker;
import static org.junit.Assert.*;

public class MarkerControllerTest {

    private MarkerController markerController;
    @Test
    public void addMarkerTest() {
        markerController = new MarkerController();
        LatLng latLng = new LatLng(28.6024274,-81.2000599);
        MarkerOptions markerOptions= markerController.addMarker(latLng);
        Assert.assertEquals(latLng,markerOptions.getPosition());
    }
}