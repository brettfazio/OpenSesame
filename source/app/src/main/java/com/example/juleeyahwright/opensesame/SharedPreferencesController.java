package com.example.juleeyahwright.opensesame;

import android.app.Activity;
import android.content.SharedPreferences;

import android.content.Context;

/*
SharedPreferencesController: stores and handles login credentials of users across different
states of the app
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

}
