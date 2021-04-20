package com.feriantes4dawin.feriavirtualmovil.ui.widgets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.feriantes4dawin.feriavirtualmovil.R;
import com.feriantes4dawin.feriavirtualmovil.ui.util.SimpleAction;
import com.feriantes4dawin.feriavirtualmovil.ui.util.SimpleAction;

import java.lang.NullPointerException;

public class YesNoDialog {

    private AppCompatActivity act;
    private String title;
    private String message;
    private SimpleAction positiveResponseFunc;
    private SimpleAction negativeResponseFunc;
    private boolean result;

    public YesNoDialog(AppCompatActivity act, String title, String message,
                       SimpleAction positiveResponseFunc,
                       SimpleAction negativeResponseFunc) {
        this.act = act;
        this.title = title;
        this.message = message;
        this.positiveResponseFunc = positiveResponseFunc;
        this.negativeResponseFunc = negativeResponseFunc;
        this.result = false;
    }

    public void show(){

        AlertDialog.Builder b = new AlertDialog.Builder(act);
        ViewGroup vg = act.findViewById(android.R.id.content);
        View v = LayoutInflater.from(act).inflate(R.layout.yes_no_dialog, vg, false);

        if(v != null){

            //Insertamos la vista al diálogo
            b.setView(v);

            AlertDialog dlg = b.create();

            TextView txtTitle = (TextView) v.findViewById(R.id.txtDlgTitle);
            TextView txtMessage = (TextView) v.findViewById(R.id.txtDlgMsg);
            Button btnYes = (Button) v.findViewById(R.id.btnDlgYes);
            Button btnNo = (Button) v.findViewById(R.id.btnDlgNo);

            txtTitle.setText(title);
            txtMessage.setText(message);

            //Para respuesta alternativa
            btnYes.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                      result = true;

                      if (positiveResponseFunc != null) positiveResponseFunc.doAction(v);

                      dlg.cancel();
                  }
            });

            //Para respuesta negativa
            btnNo.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    result = false;

                    if (negativeResponseFunc != null) negativeResponseFunc.doAction(v);

                    dlg.cancel();
                }

            });

            dlg.show();

        } else {

            throw new NullPointerException("Can't generate the required View object for AlertDialog");

        }

    }

    public boolean getResult(){
        return this.result;
    }
}