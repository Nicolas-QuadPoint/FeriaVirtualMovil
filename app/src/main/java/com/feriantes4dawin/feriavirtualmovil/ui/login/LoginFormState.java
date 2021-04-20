package com.feriantes4dawin.feriavirtualmovil.ui.login;

/**
 * Data validation state of the login form.
 */
public class LoginFormState {
    public Integer usernameError;
    public Integer passwordError;
    public Boolean isDataValid;

    public LoginFormState(){}

    public LoginFormState(Integer usernameError, Integer passwordError, Boolean isDataValid) {
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.isDataValid = isDataValid;
    }
}