package com.feriantes4dawin.feriavirtualmovil.ui.login;

/**
 * Authentication result : success (user details) or error message.
 */
public class LoginResult{
    public LoggedInUserView success;
    public Integer error;

    public LoginResult() {

    }

    public LoginResult(LoggedInUserView success, Integer error) {
        this.success = success;
        this.error = error;
    }
}