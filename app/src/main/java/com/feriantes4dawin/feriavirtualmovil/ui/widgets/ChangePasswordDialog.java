package com.feriantes4dawin.feriavirtualmovil.ui.widgets;

import com.feriantes4dawin.feriavirtualmovil.R;
import com.feriantes4dawin.feriavirtualmovil.ui.util.SimpleAction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.lang.NullPointerException;


public class ChangePasswordDialog {

    private AppCompatActivity act;
    private SimpleAction positiveResponseFunc;
    private SimpleAction negativeResponseFunc;
    private View v;
    private String passwdString;

    private MutableLiveData<ChangePasswordFormState> _passwdForm;
    private LiveData<ChangePasswordFormState> passwdFormLiveState;

    public ChangePasswordDialog(AppCompatActivity act, SimpleAction positiveResponseFunc, SimpleAction negativeResponseFunc) {
        this.act = act;
        this.positiveResponseFunc = positiveResponseFunc;
        this.negativeResponseFunc = negativeResponseFunc;
    }

    public AlertDialog generate(){

        AlertDialog.Builder b = new AlertDialog.Builder(act);
        ViewGroup vg = act.findViewById(android.R.id.content);
        this.v = LayoutInflater.from(act).inflate(R.layout.dialog_change_password, vg, false);

        if(v != null){

            //Insertamos la vista al diálogo
            b.setView(v);

            AlertDialog dlg = b.create();
            Button btnOk = (Button) v.findViewById(R.id.btnCambiarPasswd);
            Button btnCancelar = (Button) v.findViewById(R.id.btnCancelar);

            //Para respuesta alternativa
            btnOk.setOnClickListener( new View.OnClickListener(){

                @Override
                public void onClick(View view){

                    if(positiveResponseFunc != null)
                        positiveResponseFunc.doAction(v);

                    dlg.cancel();
                }

            });

            //Para respuesta negativa
            btnCancelar.setOnClickListener( new View.OnClickListener(){

                @Override
                public void onClick(View view){

                    if(negativeResponseFunc != null)
                        negativeResponseFunc.doAction(v);

                    dlg.cancel();
                }

            });

            //passwdFormLiveState.observe(act,)

            return dlg;

        } else {

            throw new NullPointerException("Can't generate the required View object for AlertDialog");

        }

    }

    public String getPassword() {

        EditText et = (EditText) v.findViewById(R.id.txtPasswd1);
        this.passwdString = et.getEditableText().toString();

        return passwdString != null? passwdString : "";

    }

    public class ChangePasswordFormState {

        int passwdError1 = 0;
        int passwdError2 = 0;
        boolean isPasswdOk = false;
    }
}