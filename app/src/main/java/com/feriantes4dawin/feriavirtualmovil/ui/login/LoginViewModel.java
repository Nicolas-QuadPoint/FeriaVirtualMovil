package com.feriantes4dawin.feriavirtualmovil.ui.login;

import android.util.Log;
import android.util.Patterns;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.feriantes4dawin.feriavirtualmovil.R;
import com.feriantes4dawin.feriavirtualmovil.data.Result;
import com.feriantes4dawin.feriavirtualmovil.data.repos.LoginRepository;
import com.feriantes4dawin.feriavirtualmovil.ui.login.LoginFormState;
import com.feriantes4dawin.feriavirtualmovil.ui.login.LoginResult;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<LoginFormState> _loginForm;
    public LoginRepository loginRepository;
    public LiveData<LoginFormState> loginFormState;
    public MutableLiveData<LoginResult> _loginResult;
    public LiveData<LoginResult> loginResult;

    public LoginViewModel(LoginRepository loginRepository) {
        this._loginForm = new MutableLiveData<LoginFormState>();
        this._loginResult = new MutableLiveData<LoginResult>();
        this.loginResult = _loginResult;
        this.loginFormState = _loginForm;
        this.loginRepository = loginRepository;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        Result result = (Result)loginRepository.login(username, password);

        if (result instanceof Result.Success) {
            _loginResult.setValue(new LoginResult(new LoggedInUserView(((Result.Success)result).data.toString()),null));

        } else {
            _loginResult.setValue(new LoginResult(null,R.string.login_failed));
        }
    }

    public void loginDataChanged(String username,String password) {
        if (!isUserNameValid(username)) {
            _loginForm.setValue(new LoginFormState(R.string.invalid_username,null,false));
        } else if (!isPasswordValid(password)) {
            _loginForm.setValue(new LoginFormState(null,R.string.invalid_password,false));
        } else {
            _loginForm.setValue(new LoginFormState(null,null,true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {

        return Patterns.EMAIL_ADDRESS.matcher(username).matches() && !username.isEmpty();

    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password){

        return password.length() > 8;

    }
}