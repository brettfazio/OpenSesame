package com.example.juleeyahwright.opensesame.AccountModel;

public interface AccountModelListener {

    void logInSuccess(String email, String password);

    void logInFailure(Exception exception, String email, String password);

    void signUpSuccess(String email, String password);

    void signUpFailure(Exception exception, String email, String password);

}
