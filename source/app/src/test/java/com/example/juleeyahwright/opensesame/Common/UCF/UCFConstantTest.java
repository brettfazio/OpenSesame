package com.example.juleeyahwright.opensesame.Common.UCF;

import com.google.android.gms.maps.model.LatLng;

import org.junit.Test;

public class UCFConstantTest {

    /*
    Testing: Points in UCF return true
    Pass Criteria: createResult returns true
     */
    @Test
    public void inUCF_test() {
        boolean res = UCFConstant.isContained(new LatLng(28.6025, -81.2017));
        assert (res);
    }

    /*
    Testing: Points outside of UCF return false
    Pass Criteria: createResult returns false
    */
    @Test
    public void notInUCF_test() {
        boolean res = UCFConstant.isContained(new LatLng(8.6025, 0.2017));
        assert (res);
    }

}