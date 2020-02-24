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
SignUpActivity: After a user has opted to sign up, enters new credentials to be stored
 */
public class SignUpActivity extends AppCompatActivity implements AccountModelListener {

    private FirebaseAuth mAuth;
    private boolean processingSignUp;
    private AccountModel accountModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        mAuth = FirebaseAuth.getInstance();
        processingSignUp = false;
        accountModel = new AccountModel(FirebaseAuth.getInstance(), this);

        // add button to sign up
        Button signUpButton = (Button) findViewById((R.id.signUpButton));
        signUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                signUpUser();
            }
        });

        // add button to go back to the login screen
        TextView backButton = (TextView) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
    // gets text from the email field entered with the soft keyboard
    private String getEnteredEmail() {
        return ((EditText) findViewById(R.id.emailField)).getText().toString();
    }

    // gets text from the password field entered with the soft keyboard
    private String getEnteredPassword() {
        return ((EditText) findViewById(R.id.passwordField)).getText().toString();
    }

    // adds the user with new credentials to the database
    private void signUpUser() {
        //TODO(): Add username to stored data.
        final String email = getEnteredEmail();
        final String password = getEnteredPassword();

        // if already processing, just return and let the other process finish
        if (processingSignUp) return;
        processingSignUp = true;

        // verifies that users don't sign up with blank fields
        if (email.length() == 0 || password.length() == 0) {
            Toast.makeText(getApplicationContext(),
                    "Email and password must both be non-empty.",
                    Toast.LENGTH_LONG).show();
            processingSignUp = false;
            return;
        }

        accountModel.signUpUser(email, password);
    }

    @Override
    public void logInSuccess(String email, String password) { }

    @Override
    public void logInFailure(Exception exception, String email, String password) { }

    @Override
    public void signUpSuccess(String email, String password) {
        SharedPreferencesController.setEmail(getApplicationContext(), email);
        SharedPreferencesController.setPassword(getApplicationContext(), password);

        Intent intent = new Intent(SignUpActivity.this, MapActivity.class);
        startActivity(intent);
        processingSignUp = false;
    }

    @Override
    public void signUpFailure(Exception exception, String email, String password) {
        Toast.makeText(getApplicationContext(),
                "Failed to sign up. Error: " + exception.toString(), Toast.LENGTH_LONG).show();

        processingSignUp = false;
    }
}
