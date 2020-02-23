package com.example.juleeyahwright.opensesame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juleeyahwright.opensesame.AccountModel.AccountModel;
import com.example.juleeyahwright.opensesame.AccountModel.AccountModelListener;
import com.example.juleeyahwright.opensesame.Map.MapActivity;
import com.google.firebase.auth.FirebaseAuth;

/*
LoginActivity: Allows the user to log into the app or sign up
 */
public class LoginActivity extends AppCompatActivity implements AccountModelListener {

    private boolean processingLogin;
    private AccountModel accountModel;
    private boolean showPopUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        processingLogin = false;
        accountModel = new AccountModel(FirebaseAuth.getInstance(), this);

        // adds login button that requires email and password are not blank
        Button loginButton = findViewById(R.id.logInButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String email = getEnteredEmail();
                String password = getEnteredPassword();
                logIn(email, password, true);
            }
        });

        // offer additional signup button to take the user to signup that does not require text input
        TextView signUpButton = findViewById((R.id.signUpButton));
        signUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                signUp();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        // on the login page, see if user is already signed in and immediately try to login
        String email = SharedPreferencesController.getEmail(getApplicationContext());
        String password = SharedPreferencesController.getPassword(getApplicationContext());

        if (email == null || password == null) {
            return;
        }

        logIn(email, password, false);
    }

    // gets text from the email field entered with the soft keyboard
    private String getEnteredEmail() {
        return ((EditText) findViewById(R.id.emailField)).getText().toString();
    }

    // gets text from the password field entered with the soft keyboard
    private String getEnteredPassword() {
        return ((EditText) findViewById(R.id.passwordField)).getText().toString();
    }

    // takes input from email and password text fields, checks that they are non-empty
    // and attempts to log the user in
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

    // takes the user to the signup page
    private void signUp() {
        Intent signUpIntent = new Intent(this, SignUpActivity.class);
        startActivity(signUpIntent);
    }

    // if login is successful, will change the email/password to the successful input
    // and take user to the map
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

    // if failure, show an error message to user
    @Override
    public void logInFailure(Exception exception, String email, String password) {
        if (showPopUp) {
            Toast.makeText(getApplicationContext(),
                    "Failed to sign in. Error: " + exception.toString(),
                    Toast.LENGTH_LONG).show();
        }
        this.processingLogin = false;
    }

    @Override
    public void signUpSuccess(String email, String password) { }
    @Override
    public void signUpFailure(Exception exception, String email, String password) { }
}
