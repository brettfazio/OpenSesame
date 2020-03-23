package com.example.juleeyahwright.opensesame.Common.UCF;

import com.google.android.gms.maps.model.LatLng;

public class UCFConstant {

    public static String UCF_WORK_ORDER_URL = "https://fo.ucf.edu/CRForm";

    public static boolean isContained(LatLng location) {
        Double WEST_LIMIT = -81.207778;
        Double SOUTH_LIMIT = 28.592286;
        Double EAST_LIMIT = -81.192197;
        Double NORTH_LIMIT = 28.611739;
        return location.latitude >= SOUTH_LIMIT
                && location.latitude <= NORTH_LIMIT
                && location.longitude >= WEST_LIMIT
                && location.longitude <= EAST_LIMIT;
    }

}
