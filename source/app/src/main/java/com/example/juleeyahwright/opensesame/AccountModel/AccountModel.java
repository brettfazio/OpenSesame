package com.example.juleeyahwright.opensesame.AccountModel;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Observable;
import java.util.concurrent.Executor;

public class AccountModel extends Observable implements Executor {

    private FirebaseAuth mAuth;
    private AccountModelListener observer;

    public AccountModel(FirebaseAuth mAuth, AccountModelListener observer) {
        this.mAuth = mAuth;
        this.observer = observer;
    }

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

    public void signUpUser(final String email, final String password) {
        //TODO(): Add username to stored data.

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
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