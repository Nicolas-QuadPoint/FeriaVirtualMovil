package com.feriantes4dawin.feriavirtualmovil.data.repos;

import com.feriantes4dawin.feriavirtualmovil.data.LoginDataSource;
import com.feriantes4dawin.feriavirtualmovil.data.Result;
import com.feriantes4dawin.feriavirtualmovil.data.models.LoggedInUser;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */


public class LoginRepository {

    private LoginDataSource dataSource;
    private LoggedInUser usuarioActivo;
    private boolean isLoggedIn;

    public LoginRepository(LoginDataSource ds){
        this.dataSource = ds;
        this.usuarioActivo = null;
        this.isLoggedIn = false;
    }

    public void logout() {
        usuarioActivo = null;
        dataSource.logout();
    }

    public Result<LoggedInUser> login(String username,String password){
        // handle login
        Result result = dataSource.login(username, password);

        if (result instanceof Result.Success) {
            Result.Success s = (Result.Success)result;
            setLoggedInUser((LoggedInUser) s.data);
        }

        return result;
    }

    private void setLoggedInUser(LoggedInUser loggedInUser) {
        this.usuarioActivo = loggedInUser;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}