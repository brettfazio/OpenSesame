package com.example.juleeyahwright.opensesame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

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

        // Link UI
        Button signUpButton = (Button) findViewById((R.id.signUpButton));
        signUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                signUpUser();
            }
        });

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    private String getEnteredEmail() {
        return ((EditText) findViewById(R.id.emailField)).getText().toString();
    }

    private String getEnteredPassword() {
        return ((EditText) findViewById(R.id.passwordField)).getText().toString();
    }

    private void signUpUser() {
        //TODO(): Add username to stored data.
        final String email = getEnteredEmail();
        final String password = getEnteredPassword();

        if (processingSignUp) return;
        processingSignUp = true;

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

        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
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
