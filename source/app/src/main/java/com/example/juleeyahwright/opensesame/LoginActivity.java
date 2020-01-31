package com.example.juleeyahwright.opensesame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mAuth = FirebaseAuth.getInstance();

        // Link UI
        Button loginButton = (Button) findViewById(R.id.logInButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                logIn();
            }
        });
        Button signUpButton = (Button) findViewById((R.id.signUpButton));
        signUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                signUp();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        // A user is signed in
        if (currentUser != null) {
            // Proceed to main app.
        }


    }

    public void logIn() {
        // Get the email and password the user entered.
        String email = ((EditText) findViewById(R.id.usernameField)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordField)).getText().toString();

        if (email.length() == 0 || password.length() == 0) {
            System.out.println("here");
            Toast.makeText(getApplicationContext(),
                    "Email and password must both be non-empty.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            //Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                            //        Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),
                                    "Failed to sign in.",
                                    Toast.LENGTH_LONG).show();
                            //updateUI(null);
                        }

                    }
                });
    }

    public void signUp() {
        Intent signUpIntent = new Intent(this, SignUpActivity.class);
        startActivity(signUpIntent);
    }

}
