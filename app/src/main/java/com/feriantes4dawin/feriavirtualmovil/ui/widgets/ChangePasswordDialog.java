package com.feriantes4dawin.feriavirtualmovil.ui.widgets;

import com.feriantes4dawin.feriavirtualmovil.R;
import com.feriantes4dawin.feriavirtualmovil.ui.util.SimpleAction;
import com.feriantes4dawin.feriavirtualmovil.ui.util.SimpleTextWatcher;

import android.content.DialogInterface;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class ChangePasswordDialog extends SimpleDialog{

    private String passwdString;
    //private MutableLiveData<ObjetoModificacionContrasena> datosMutablesContrasena;

    public ChangePasswordDialog(AppCompatActivity act,
      //                          MutableLiveData<ObjetoModificacionContrasena> datosMutablesContrasena,
                                SimpleAction positiveResponseFunc,
                                SimpleAction negativeResponseFunc) {
        super(act,positiveResponseFunc,negativeResponseFunc);

       // this.datosMutablesContrasena = datosMutablesContrasena;
    }

    public String getPasswdString(){
        return this.passwdString;
    }

    @Override
    protected View prepareView() {

        ViewGroup vg = act.findViewById(android.R.id.content);
        this.v = LayoutInflater.from(act).inflate(R.layout.dialog_change_password, vg, false);
        dlg.setTitle(act.getString(R.string.mpf_setting_change_passwd));

        EditText txtPass1 = (EditText) v.findViewById(R.id.dcp_txtPasswd2);
        EditText txtPass2 = (EditText) v.findViewById(R.id.dcp_txtPasswd3);

        txtPass1.addTextChangedListener(new ChangePasswordTextWatcher(txtPass1,txtPass2));
        txtPass2.addTextChangedListener(new ChangePasswordTextWatcher(txtPass2,txtPass1));

        return this.v;
    }

    @Override
    public AlertDialog generate() {
        AlertDialog ad = super.generate();

        ad.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {

                ad.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);

            }
        });

        return ad;
    }

    @Override
    protected void prepareResponses() {

        dlg.setButton(AlertDialog.BUTTON_POSITIVE,act.getString(R.string.action_change),
            (dialogo,idBoton) ->{

                if (positiveResponseFunc != null) {
                    positiveResponseFunc.doAction(v);
                }

                dlg.cancel();

            }
        );

        dlg.setButton(AlertDialog.BUTTON_NEGATIVE,act.getString(R.string.action_cancel),
            (dialogo,idBoton) ->{

                this.passwdString = null;

                if (negativeResponseFunc != null) {
                    negativeResponseFunc.doAction(v);
                }

                dlg.cancel();

            }
        );

    }



    public class ChangePasswordTextWatcher extends SimpleTextWatcher {

        private EditText txtPasswd2;

        public ChangePasswordTextWatcher(EditText txtPasswd1,EditText txtPasswd2){
            super(txtPasswd1);
            this.txtPasswd2 = txtPasswd2;
        }

        @Override
        public void afterTextChanged(Editable e) {

            if(isPasswdValid(e) && isPasswdValid(txtPasswd2.getText()) &&
            e.toString().equals(txtPasswd2.getText().toString())){

                txtPasswd2.setError(null);
                txt.setError(null);
                dlg.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);

            } else {

                txtPasswd2.setError(act.getString(R.string.err_mes_passwd_does_not_match));
                dlg.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                passwdString = txtPasswd2.getText().toString();

            }
        }

        private boolean isPasswdValid(Editable e){

            return (e.length() > 5 || !e.toString().isEmpty());

        }
    }

}