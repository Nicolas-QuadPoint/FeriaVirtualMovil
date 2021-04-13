package com.feriantes4dawin.feriavirtual.ui.util

import android.content.Intent
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity

class UtilityFunctions {

    //Funciones estaticas
    companion object{

        /**
         * Concepto y paso a paso extraido de:
         * https://www.tutorialspoint.com/how-to-pick-an-image-from-image-gallery-in-android
         *
         * Para que esto tenga efecto, la actividad que invoque este método debe sobrescribir
         * el método onActivityResult para obtener la información de la imágen.
         * La aplicación debe tener concedido el permiso para leer archivos. Vease esto en
         * el archivo de manifiesto
         *
         * @param act Un objeto AppCompatActivity, no nulo.
         */
        fun getImageFromGallery(act : AppCompatActivity){

            //Esto generara un dialogo para acceder a la galeria y escoger una imagen. La cual sera entregada
            //en forma de ubicación absoluta
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)

            //Vamos mostrando el dialogo!
            act.startActivityForResult(galleryIntent,FeriaVirtualConstants.ESCOGER_IMAGEN_REQUEST)
        }
    }


}