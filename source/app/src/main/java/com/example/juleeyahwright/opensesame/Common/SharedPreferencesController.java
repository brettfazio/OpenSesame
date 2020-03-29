package com.example.juleeyahwright.opensesame.Common;

import android.app.Activity;
import android.content.SharedPreferences;

import android.content.Context;

/*
SharedPreferencesController: stores and handles written data across different states of the app
 */
public class SharedPreferencesController extends Activity {
    private static final String CONTEXT_NAME = "Login";

    private static final int INT_UNAVAILABLE = -1;

    private static SharedPreferences spFromContext(Context context) {
        return context.getSharedPreferences(CONTEXT_NAME, MODE_PRIVATE);
    }

    public static String getEmail(Context context) {
        return spFromContext(context).getString("email", null);
    }

    public static String getPassword(Context context) {
        return spFromContext(context).getString("password", null);
    }

    public static void setEmail(Context context, String email) {
        SharedPreferences sp = spFromContext(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("email", email);
        editor.apply();
    }

    public static void setPassword(Context context, String password) {
        SharedPreferences sp = spFromContext(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("password", password);
        editor.apply();
    }

    public static boolean isLoginCredentialsSet(Context context) {
        return getEmail(context) != null && getPassword(context) != null;
    }

    public static void clearSignInData(Context context) {
        SharedPreferences sp = spFromContext(context);
        sp.edit().clear().apply();
    }

    public static void setColor(Context context, int color) {
        SharedPreferences sp = spFromContext(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("color", color);
        editor.apply();
    }

    public static int getColor(Context context) {
        return spFromContext(context).getInt("email", INT_UNAVAILABLE);
    }

    public static void setTheme(Context context, int theme) {
        SharedPreferences sp = spFromContext(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("theme", theme);
        editor.apply();
    }

    public static int getTheme(Context context) {
        return spFromContext(context).getInt("theme", INT_UNAVAILABLE);
    }

    public static void setMapType(Context context, String type) {
        SharedPreferences sp = spFromContext(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("mapType", type);
        editor.apply();
    }

    public static String getMapType(Context context) {
        return spFromContext(context).getString("mapType", null);
    }

    public static void isSatelliteChecked(Context context, Boolean set) {
        SharedPreferences sp = spFromContext(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("satelliteChecked", set);
        editor.apply();
    }

    public static boolean getSatelliteChecked(Context context) {
        return spFromContext(context).getBoolean("satelliteChecked", false);
    }

    public static void setMapTheme(Context context, String theme) {
        SharedPreferences sp = spFromContext(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("mapTheme", theme);
        editor.apply();
    }

    public static String getMapTheme(Context context) {
        return spFromContext(context).getString("mapTheme", null);
    }

}
