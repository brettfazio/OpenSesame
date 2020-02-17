package com.example.juleeyahwright.opensesame;

import android.location.Location;
import org.junit.BeforeClass;
import com.example.juleeyahwright.opensesame.Map.MapActivity;
import com.example.juleeyahwright.opensesame.Map.MapController;
import com.google.android.gms.maps.GoogleMap;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;

public class MapControllerTest {

    @Mock
    private static GoogleMap mMap;
    @Mock
    private static MapActivity mapActivity;

    private static MapController mapController;
    private static Location l;

    @BeforeClass
    public static void mapControllerTestSetup() {
        mapController = new MapController(mMap, mapActivity);
        l = new Location("Test Location");
    }

    @Test
    public void getLocationTest() {
        mapController.setLocation(l);
        assertEquals(mapController.getLocation(), l);
    }

    @Test
    public void getMapTest() {
        assertEquals(mapController.getMap(), mMap);
    }

    @Test
    public void getLocationPermissionTest() {
        mapController.setLocationPermission(true);
        assertEquals(mapController.getLocationPermission(), true);
    }
}