package com.example.juleeyahwright.opensesame;

import android.content.SharedPreferences;
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

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        mAuth = FirebaseAuth.getInstance();

        // Link UI
        Button signUpButton = (Button) findViewById((R.id.signUpButton));
        signUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                signUpUser();
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

        if (email.length() == 0 || password.length() == 0) {
            Toast.makeText(getApplicationContext(),
                    "Email and password must both be non-empty.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            // Save to shared preferences
                            SharedPreferences sp =
                                    getSharedPreferences("Login", MODE_PRIVATE);
                            SharedPreferences.Editor Ed = sp.edit();
                            Ed.putString("email", email);
                            Ed.putString("password", password);
                            Ed.commit();

                            //updateUI(user);
                        } else {
                            System.out.println(task.getException().toString());
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            //Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                            //        Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

}
