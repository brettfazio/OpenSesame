package com.example.juleeyahwright.opensesame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements AccountModelListener {

    private FirebaseAuth mAuth;
    private boolean processingLogin;
    private AccountModel accountModel;
    private boolean showPopUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mAuth = FirebaseAuth.getInstance();
        processingLogin = false;
        accountModel = new AccountModel(FirebaseAuth.getInstance(), this);

        // Link UI
        Button loginButton = findViewById(R.id.logInButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String email = getEnteredEmail();
                String password = getEnteredPassword();
                logIn(email, password, true);
            }
        });
        Button signUpButton = findViewById((R.id.signUpButton));
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
        if (processingLogin) return;
        this.processingLogin = true;
        this.showPopUp = showPopUp;
        if (email.length() == 0 || password.length() == 0) {
            Toast.makeText(getApplicationContext(),
                    "Email and password must both be non-empty.",
                    Toast.LENGTH_LONG).show();
            this.processingLogin = false;
            return;
        }

        accountModel.logIn(email, password);
    }

    private void signUp() {
        Intent signUpIntent = new Intent(this, SignUpActivity.class);
        startActivity(signUpIntent);
    }

    @Override
    public void logInSuccess(String email, String password) {
        boolean differentPassword = SharedPreferencesController.getPassword(getApplicationContext()) == null
                || !SharedPreferencesController.getPassword(getApplicationContext()).equals(password);
        boolean differentEmail = SharedPreferencesController.getEmail(getApplicationContext()) == null
                || !SharedPreferencesController.getEmail(getApplicationContext()).equals(email);

        if (SharedPreferencesController.isLoginCredentialsSet(getApplicationContext())
                || differentEmail || differentPassword) {
            SharedPreferencesController.setEmail(getApplicationContext(), email);
            SharedPreferencesController.setPassword(getApplicationContext(), password);
        }

        Intent intent = new Intent(LoginActivity.this, MapActivity.class);
        startActivity(intent);
        this.processingLogin = false;
    }

    @Override
    public void logInFailure(Exception exception, String email, String password) {
        if (showPopUp) {
            Toast.makeText(getApplicationContext(),
                    "Failed to sign in.",
                    Toast.LENGTH_LONG).show();
        }
        this.processingLogin = false;
    }

    @Override
    public void signUpSuccess(String email, String password) { }
    @Override
    public void signUpFailure(Exception exception, String email, String password) { }
}
