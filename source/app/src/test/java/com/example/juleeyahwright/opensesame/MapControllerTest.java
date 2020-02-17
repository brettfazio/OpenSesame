package com.example.juleeyahwright.opensesame;

import android.location.Location;

import com.example.juleeyahwright.opensesame.Map.MapActivity;
import com.example.juleeyahwright.opensesame.Map.MapController;
import com.google.android.gms.maps.GoogleMap;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;

public class MapControllerTest {

    @Mock
    private GoogleMap mMap;
    @Mock
    private MapActivity mapActivity;

    private MapController mapController;

    @Test
    public void MapController_Test() {
        mapController = new MapController(mMap, mapActivity);
        Location l = new Location("Test Location");

        mapController.setLocation(l);
        mapController.setLocationPermission(true);

        assertEquals(mapController.getLocation(), l);
        assertEquals(mapController.getMap(), mMap);
        assertEquals(mapController.getLocationPermission(), true);
    }
}