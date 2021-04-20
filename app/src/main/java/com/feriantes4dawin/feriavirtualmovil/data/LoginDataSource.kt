package com.feriantes4dawin.feriavirtualmovil.data

import com.feriantes4dawin.feriavirtual.data.model.LoggedInUser
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): _root_ide_package_.com.feriantes4dawin.feriavirtualmovil.data.Result<LoggedInUser> {

        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Usuario promedio")
            return _root_ide_package_.com.feriantes4dawin.feriavirtualmovil.data.Result.Success(
                fakeUser
            )
        } catch (e: Throwable) {
            return _root_ide_package_.com.feriantes4dawin.feriavirtualmovil.data.Result.Error(
                IOException("Error logging in", e)
            )
        }

    }

    fun logout() {
        // TODO: revoke authentication
    }
}