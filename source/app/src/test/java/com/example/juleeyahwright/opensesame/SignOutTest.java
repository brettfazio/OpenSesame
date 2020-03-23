package com.example.juleeyahwright.opensesame;

import android.app.Activity;
import android.view.Menu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.juleeyahwright.opensesame.AccountModel.AccountModel;
import com.example.juleeyahwright.opensesame.AccountModel.AccountModelListener;
import com.example.juleeyahwright.opensesame.Common.SharedPreferencesController;
import com.example.juleeyahwright.opensesame.Map.MapActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Executor;


public class SignOutTest implements AccountModelListener {

    private Task<AuthResult> successTask;
    private Task<AuthResult> failureTask;

    @Mock
    private FirebaseAuth mAuth;

    private AccountModel accountModel;

    // Firebase unit test setup
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        successTask = new Task<AuthResult>() {
            @Override
            public boolean isComplete() {
                return true;
            }

            @Override
            public boolean isSuccessful() {
                return true;
            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Nullable
            @Override
            public AuthResult getResult() {
                return null;
            }

            @Nullable
            @Override
            public <X extends Throwable> AuthResult getResult(@NonNull Class<X> aClass) throws X {
                return null;
            }

            @Nullable
            @Override
            public Exception getException() {
                return null;
            }

            @NonNull
            @Override
            public Task<AuthResult> addOnSuccessListener(@NonNull OnSuccessListener<? super AuthResult> onSuccessListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<AuthResult> addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener<? super AuthResult> onSuccessListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<AuthResult> addOnSuccessListener(@NonNull Activity activity, @NonNull OnSuccessListener<? super AuthResult> onSuccessListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<AuthResult> addOnFailureListener(@NonNull OnFailureListener onFailureListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<AuthResult> addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<AuthResult> addOnFailureListener(@NonNull Activity activity, @NonNull OnFailureListener onFailureListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<AuthResult> addOnCompleteListener(@NonNull Executor executor, @NonNull OnCompleteListener<AuthResult> onCompleteListener) {
                onCompleteListener.onComplete(successTask);
                return successTask;
            }
        };
        failureTask = new Task<AuthResult>() {
            @Override
            public boolean isComplete() {
                return true;
            }

            @Override
            public boolean isSuccessful() {
                return false;
            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Nullable
            @Override
            public AuthResult getResult() {
                return null;
            }

            @Nullable
            @Override
            public <X extends Throwable> AuthResult getResult(@NonNull Class<X> aClass) throws X {
                return null;
            }

            @Nullable
            @Override
            public Exception getException() {
                return null;
            }

            @NonNull
            @Override
            public Task<AuthResult> addOnSuccessListener(@NonNull OnSuccessListener<? super AuthResult> onSuccessListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<AuthResult> addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener<? super AuthResult> onSuccessListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<AuthResult> addOnSuccessListener(@NonNull Activity activity, @NonNull OnSuccessListener<? super AuthResult> onSuccessListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<AuthResult> addOnFailureListener(@NonNull OnFailureListener onFailureListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<AuthResult> addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<AuthResult> addOnFailureListener(@NonNull Activity activity, @NonNull OnFailureListener onFailureListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<AuthResult> addOnCompleteListener(@NonNull Executor executor, @NonNull OnCompleteListener<AuthResult> onCompleteListener) {
                onCompleteListener.onComplete(failureTask);
                return failureTask;
            }
        };

        accountModel = new AccountModel(mAuth, this);
    }


    @Test
    public void signOutTest() {
        String email = "User@email.com";
        String password = "Wright10";

        Mockito.when(mAuth.signInWithEmailAndPassword(email, password)).thenReturn(successTask);

        accountModel.logIn(email, password);

        try {
            mAuth.signOut();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logInSuccess(String email, String password){
    }

    @Override
    public void logInFailure(Exception exception, String email, String password) {
    }

    @Override
    public void signUpSuccess(String email, String password) {
    }

    @Override
    public void signUpFailure(Exception exception, String email, String password) {
    }
}


