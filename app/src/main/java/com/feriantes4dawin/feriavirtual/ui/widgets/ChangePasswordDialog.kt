package com.feriantes4dawin.feriavirtual.ui.widgets

import com.feriantes4dawin.feriavirtual.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import java.lang.NullPointerException


class ChangePasswordDialog(var act:AppCompatActivity,
                           var positiveResponseFunc : ((v:View) -> Unit)?,
                           var negativeResponseFunc : ((v:View) -> Unit)? ) {

    private var v : View? = null
    private var passwdString : String? = null

    private var _passwdForm = MutableLiveData<ChangePasswordFormState>()
    private var passwdFormLiveState : LiveData<ChangePasswordFormState> = _passwdForm

    fun generate() : AlertDialog {

        val b : AlertDialog.Builder = AlertDialog.Builder(act)
        val vg : ViewGroup = act.findViewById(android.R.id.content)
        v = LayoutInflater.from(act).inflate(R.layout.change_password_dialog, vg, false)



        if(v != null){

            //Insertamos la vista al diálogo
            b.setView(v)

            val dlg :AlertDialog = b.create()
            val btnOk : Button = v!!.findViewById<Button>(R.id.btnCambiarPasswd)
            val btnCancelar : Button = v!!.findViewById<Button>(R.id.btnCancelar)

            //Para respuesta alternativa
            btnOk.setOnClickListener {

                if(positiveResponseFunc != null)
                    positiveResponseFunc!!(this.v!!)

                dlg.cancel()
            }

            //Para respuesta negativa
            btnCancelar.setOnClickListener {
                if(negativeResponseFunc != null)
                    negativeResponseFunc!!(this.v!!)

                dlg.cancel()
            }

            //passwdFormLiveState.observe(act,)




            return dlg

        } else {

            throw NullPointerException("Can't generate the required View object for AlertDialog")

        }

    }

    fun getPassword() : String {

        passwdString = v?.findViewById<EditText>(R.id.txtPasswd1)?.text.toString()

        return if(passwdString != null) {

            passwdString!!

        } else {

            ""

        }

    }

    data class ChangePasswordFormState (

        val passwdError1 : Int? = null,
        val passwdError2 : Int? = null,
        val isPasswdOk : Boolean = false

    )



}