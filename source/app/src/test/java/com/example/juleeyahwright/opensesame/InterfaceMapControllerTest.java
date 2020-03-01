package com.example.juleeyahwright.opensesame;

import android.location.Location;

import com.example.juleeyahwright.opensesame.Map.InterfaceMapController;
import com.example.juleeyahwright.opensesame.Map.MapActivity;
import com.google.android.gms.maps.GoogleMap;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InterfaceMapControllerTest {

    @Mock
    private static GoogleMap mMap;
    @Mock
    private static MapActivity mapActivity;

    private static InterfaceMapController interfaceMapController;
    private static Location l;

    /*
    Adds a map and a location to test from
     */
    @BeforeClass
    public static void mapControllerTestSetup() {
        interfaceMapController = new InterfaceMapController(mMap, mapActivity);
        l = new Location("Test Location");
    }

    /*
    Testing: Location of the device is accessed correctly
    Pass Criteria: location matches actual location
     */
    @Test
    public void getLocationTest() {
        interfaceMapController.setLocation(l);
        assertEquals(interfaceMapController.getLocation(), l);
    }

    /*
    Testing: Map is present
    Pass Criteria: Map is displayed
     */
    @Test
    public void getMapTest() {
        assertEquals(interfaceMapController.getMap(), mMap);
    }

    /*
    Testing: When Locations Permissions are set to true, location permissions are stored as true
    Pass Criteria: Location permissions are true
     */
    @Test
    public void getLocationPermissionTest() {
        interfaceMapController.setLocationPermission(true);
        assertTrue(interfaceMapController.getLocationPermission());
    }
}