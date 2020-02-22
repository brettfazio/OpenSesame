package com.example.juleeyahwright.opensesame;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.juleeyahwright.opensesame.Map.MapActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*
LaunchActivity: determines if user is logged in and if so takes them to the map and if not takes the
user to the login screen
 */
public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // check if user has logged in before
        final String email = SharedPreferencesController.getEmail(getApplicationContext());
        final String password = SharedPreferencesController.getPassword(getApplicationContext());

        // if a field is missing, send the user to login
        if (email == null || password == null) {
            intentLogin();
            return;
        }

        // if email and password are present
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            // Set login preferences if they are different.
                            if (SharedPreferencesController.isLoginCredentialsSet(getApplicationContext())
                                    || !SharedPreferencesController.getPassword(getApplicationContext()).equals(password)
                                    || !SharedPreferencesController.getEmail(getApplicationContext()).equals(email)) {
                                SharedPreferencesController.setEmail(getApplicationContext(), email);
                                SharedPreferencesController.setPassword(getApplicationContext(), password);
                            }

                            System.out.println("EMAIL " + user.getEmail());

                            intentMap();
                        } else {
                            intentLogin();
                        }

                    }
                });
    }

    // send the user to the map
    private void intentMap() {
        Intent intent = new Intent(LaunchActivity.this, MapActivity.class);
        startActivity(intent);
    }

    // send the user to the login screen
    private void intentLogin() {
        Intent intent = new Intent(LaunchActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}
