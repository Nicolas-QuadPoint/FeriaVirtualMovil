package com.feriantes4dawin.feriavirtual.ui.widgets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.feriantes4dawin.feriavirtual.R
import java.lang.NullPointerException

class YesNoDialog (var act: AppCompatActivity,var title : String,var message : String ,
                   var positiveResponseFunc : (() -> Unit)?,
                   var negativeResponseFunc : (() -> Unit)? ) {

    private var result = false

    fun show() {

        val b : AlertDialog.Builder = AlertDialog.Builder(act)
        val vg : ViewGroup = act.findViewById(android.R.id.content)
        val v = LayoutInflater.from(act).inflate(R.layout.yes_no_dialog, vg, false)

        if(v != null){

            //Insertamos la vista al diálogo
            b.setView(v)

            val dlg : AlertDialog = b.create()

            val txtTitle : TextView = v.findViewById<Button>(R.id.txtDlgTitle)
            val txtMessage : TextView = v.findViewById<Button>(R.id.txtDlgMsg)
            val btnYes : Button = v.findViewById<Button>(R.id.btnDlgYes)
            val btnNo : Button = v.findViewById<Button>(R.id.btnDlgNo)

            txtTitle.text = title
            txtMessage.text = message

            //Para respuesta alternativa
            btnYes.setOnClickListener {

                result = true
                if( positiveResponseFunc != null )
                    positiveResponseFunc!!()

                dlg.cancel()
            }

            //Para respuesta negativa
            btnNo.setOnClickListener {

                result = false
                if( negativeResponseFunc != null )
                    negativeResponseFunc!!()

                dlg.cancel()
            }

            dlg.show()

        } else {

            throw NullPointerException("Can't generate the required View object for AlertDialog")

        }

    }

    fun getResult() : Boolean {

        return result
    }



}