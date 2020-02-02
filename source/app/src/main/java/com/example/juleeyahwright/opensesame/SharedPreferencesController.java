package com.example.juleeyahwright.opensesame;

import android.app.Activity;
import android.content.SharedPreferences;

import android.content.Context;

public class SharedPreferencesController extends Activity {
    private static final String CONTEXT_NAME = "Login";

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
        SharedPreferences.Editor Ed = sp.edit();
        Ed.putString("email", email);
        Ed.commit();
    }

    public static void setPassword(Context context, String password) {
        SharedPreferences sp = spFromContext(context);
        SharedPreferences.Editor Ed = sp.edit();
        Ed.putString("password", password);
        Ed.commit();
    }

    public static boolean isLoginCredentialsSet(Context context) {
        SharedPreferences sp = spFromContext(context);

        if (getEmail(context) == null || getPassword(context) == null) {
            return false;
        }

        return true;
    }


}
