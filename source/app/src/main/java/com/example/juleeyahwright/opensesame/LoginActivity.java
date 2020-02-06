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

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mAuth = FirebaseAuth.getInstance();

        // Link UI
        Button loginButton = (Button) findViewById(R.id.logInButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String email = getEnteredEmail();
                String password = getEnteredPassword();
                logIn(email, password, true);
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

        String email = SharedPreferencesController.getEmail(getApplicationContext());
        String password = SharedPreferencesController.getPassword(getApplicationContext());

        if (email == null || password == null) {
            return;
        }

        logIn(email, password, false);
    }

    private String getEnteredEmail() {
        return ((EditText) findViewById(R.id.emailField)).getText().toString();
    }

    private String getEnteredPassword() {
        return ((EditText) findViewById(R.id.passwordField)).getText().toString();
    }

    private void logIn(final String email, final String password, final boolean showPopUp) {
        if (email.length() == 0 || password.length() == 0) {
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

                            // Set login preferences if they are different.

                            boolean differentPassword = SharedPreferencesController.getPassword(getApplicationContext()) == null ?
                                    true : !SharedPreferencesController.getPassword(getApplicationContext()).equals(password);
                            boolean differentEmail= SharedPreferencesController.getEmail(getApplicationContext()) == null ?
                                    true : !SharedPreferencesController.getEmail(getApplicationContext()).equals(email);

                            if (SharedPreferencesController.isLoginCredentialsSet(getApplicationContext())
                                    || differentEmail || differentPassword) {
                                SharedPreferencesController.setEmail(getApplicationContext(), email);
                                SharedPreferencesController.setPassword(getApplicationContext(), password);
                            }

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            //Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                            //        Toast.LENGTH_SHORT).show();
                            if (showPopUp) {
                                Toast.makeText(getApplicationContext(),
                                        "Failed to sign in.",
                                        Toast.LENGTH_LONG).show();
                            }
                            //updateUI(null);
                        }

                    }
                });
    }

    private void signUp() {
        Intent signUpIntent = new Intent(this, SignUpActivity.class);
        startActivity(signUpIntent);
    }

}
