package com.example.juleeyahwright.opensesame;

import android.content.Context;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;

import com.example.juleeyahwright.opensesame.Map.MapActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import androidx.appcompat.view.menu.MenuBuilder;


public class SignOutTest {

    @Mock
    private Menu menu;

    @Mock
    private static MapActivity mapActivity;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mapActivity = Mockito.mock(MapActivity.class);
        Mockito.when(mapActivity.onCreateOptionsMenu(menu)).thenReturn(true);
    }


    @Test
    public void signOutSuccessful(){
        // stuck figuring out how to initialize item and menu

        menu.add(R.id.sign_out_option);
        Assert.assertEquals(true, mapActivity.onOptionsItemSelected(menu.getItem(R.id.sign_out_option)));

    }
}

