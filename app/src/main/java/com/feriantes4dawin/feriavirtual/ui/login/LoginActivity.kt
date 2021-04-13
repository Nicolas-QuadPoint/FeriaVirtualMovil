package com.feriantes4dawin.feriavirtual.ui.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.Spanned
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat.TO_HTML_PARAGRAPH_LINES_INDIVIDUAL
import androidx.core.text.HtmlCompat.fromHtml
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.feriantes4dawin.feriavirtual.R
import com.feriantes4dawin.feriavirtual.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {

    //private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginViewModel : LoginViewModel
    private var loginViewModelFactory  = LoginViewModelFactory()

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        loginViewModel = ViewModelProvider(this,loginViewModelFactory)
            .get(LoginViewModel::class.java)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val loading = findViewById<ProgressBar>(R.id.loading)
        val quit = findViewById<Button>(R.id.btnQuit)

        val linkRegistro = findViewById<TextView>(R.id.tvLinkRegistro)
        lateinit var textolink:Spanned

        textolink = fromHtml(
        "<a href='https://www.google.com/?hl=es'>${resources.getString(R.string.launch_user_registry_web)}</a>",
            TO_HTML_PARAGRAPH_LINES_INDIVIDUAL)

        //Le da el aspecto y acciones de un enlace
        linkRegistro.text = textolink
        linkRegistro.movementMethod = LinkMovementMethod.getInstance()

        /*
        linkRegistro.setOnClickListener {

            val uri = "https://www.google.com/?hl=es"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(uri)
            startActivity(intent)

        }*/

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {

                updateUiWithUser(loginResult.success)

                //Cambiamos a la actividad principal
                val menuprincipalintent = Intent(this, MainActivity::class.java)

                startActivity(menuprincipalintent)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString())
            }
        }

        quit.setOnClickListener {
            finish()
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}