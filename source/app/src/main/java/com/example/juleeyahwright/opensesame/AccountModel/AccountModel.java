package com.example.juleeyahwright.opensesame.AccountModel;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Observable;
import java.util.concurrent.Executor;
/*
AccountModel: logs in and signs up the user and interacts with the user database to authenticate
with Firebase
 */
public class AccountModel extends Observable implements Executor {

    private final FirebaseAuth mAuth;
    private final AccountModelListener observer;

    public AccountModel(FirebaseAuth mAuth, AccountModelListener observer) {
        this.mAuth = mAuth;
        this.observer = observer;
    }

    public AccountModel(AccountModelListener observer) {
        this.mAuth = FirebaseAuth.getInstance();
        this.observer = observer;
    }

    // logs in only if successful authentication with Firebase
    public void logIn(final String email, final String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            observer.logInSuccess(email, password);

                        } else {
                            observer.logInFailure(task.getException(), email, password);
                        }

                    }
                });
    }

    // signs the user up and enters their data into the database 
    public void signUpUser(final String email, final String password) {
        //TODO(): Add username to stored data.

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            observer.signUpSuccess(email, password);
                        } else {
                            observer.signUpFailure(task.getException(), email, password);
                        }
                    }
                });
    }


    @Override
    public void execute(Runnable runnable) {
        new Thread(runnable).start();
    }
}
