package com.feriantes4dawin.feriavirtualmovil.data;

import android.util.Log;

import com.feriantes4dawin.feriavirtualmovil.data.model.LoggedInUser;
import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username,String password) {

        Result<LoggedInUser> r = new Result<LoggedInUser>();

        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser fakeUser = new LoggedInUser(java.util.UUID.randomUUID().toString(), "Usuario promedio");
            return r.new Success<LoggedInUser>(fakeUser);

        } catch (Throwable t) {

            return r.new Error<LoggedInUser>(
                new IOException("Error logging in", t)
            );

        }

    }

    public void logout() {
        // TODO: revoke authentication
    }
}