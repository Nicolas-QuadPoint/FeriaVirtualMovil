package com.feriantes4dawin.feriavirtual.ui.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.feriantes4dawin.feriavirtual.R
import com.feriantes4dawin.feriavirtual.data.db.FeriaVirtualDatabase
import com.feriantes4dawin.feriavirtual.data.network.UsuarioDataSourceImpl
import com.feriantes4dawin.feriavirtual.data.repos.UsuarioRepositoryImpl
import com.feriantes4dawin.feriavirtual.dependencies.FeriaVirtualAPIProvider
import com.feriantes4dawin.feriavirtual.ui.util.UtilityFunctions
import com.feriantes4dawin.feriavirtual.ui.widgets.ChangePasswordDialog
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyProfileFragment : Fragment() {

    //override val di by closestDI()

    //private val myProfileViewModelFactory by instance<MyProfileViewModelFactory>()
    private lateinit var myProfileViewModelFactory : MyProfileViewModelFactory

    private lateinit  var myProfileViewModel : MyProfileViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        //Obtenermos la vista con todos los controles
        val root = inflater.inflate(R.layout.fragment_my_profile, container, false)

        //Retornamos la vista para que sea dibujada
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Asignamos eventos a los controles
        view.findViewById<Button>(R.id.btnCambiarContrasena)?.setOnClickListener { v:View -> btnCambiarContrasenaClick(v) }
        view.findViewById<Button>(R.id.btnGuardarCambiosPerfil)?.setOnClickListener { v:View ->  btnGuardarCambiosPerfilClick(v) }
        view.findViewById<Button>(R.id.btnCancelarCambiosPerfil)?.setOnClickListener { v:View ->  btnCancelarCambiosPerfilClick(v) }
        view.findViewById<AppCompatImageButton>(R.id.btnFotoPerfilUsuario)?.setOnClickListener { v:View ->  btnFotoPerfilUsuarioClick(v) }

        //TODO:Borrar esto cuando se implemente la arquitectura mvvm text_home
        getInfoFromAPI(view)

        val miSwiper = view.findViewById<SwipeRefreshLayout>(R.id.swipeMyProfile)

        miSwiper.setOnRefreshListener {

            getInfoFromAPI(view)
            miSwiper.isRefreshing = false
        }



    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        myProfileViewModelFactory  = MyProfileViewModelFactory(
            UsuarioRepositoryImpl(
                (FeriaVirtualDatabase.getInstance(context)).usuarioDAO(),
                UsuarioDataSourceImpl((FeriaVirtualAPIProvider.provideUsuarioAPI()))
            )
        )

        myProfileViewModel =
            ViewModelProvider(this,myProfileViewModelFactory).get(MyProfileViewModel::class.java)

    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return super.onCreateAnimation(transit, enter, nextAnim)
    }

    fun btnCambiarContrasenaClick(v : View){

        try {

            val cpd = ChangePasswordDialog(this.activity as AppCompatActivity, { _ : View ->

                Snackbar.make(this@MyProfileFragment.requireView(),
                        this@MyProfileFragment.getString(R.string.mpf_setting_passwd),Snackbar.LENGTH_LONG).show()

            },null)

            //Mostramos el dialogo
            val d = cpd.generate()

            //Abrimos el dialogo!
            d.show()

            //Extraimos la contraseña, y la modificamos!
            Log.i("[MY_PROFILE_FRAGMENT]",cpd.getPassword())

            //Cerramos el dialogo
            //d.cancel()

        } catch (_ : Exception){

            Toast.makeText(this.activity,R.string.err_msg_generic,Toast.LENGTH_SHORT).show()

        }

    }

    fun btnFotoPerfilUsuarioClick(v : View){

        Toast.makeText(this.activity,"Deberia cambiar imagen ¿No?",Toast.LENGTH_SHORT).show()
        UtilityFunctions.getImageFromGallery( this.activity as AppCompatActivity )

    }

    fun btnGuardarCambiosPerfilClick(v : View){

        Toast.makeText(this.activity,"Deberia guardar cambios ¿No?",Toast.LENGTH_SHORT).show()

    }

    fun btnCancelarCambiosPerfilClick(v : View){

        Toast.makeText(this.activity,"Deberia cancelar cambios ¿No?",Toast.LENGTH_SHORT).show()

    }

    private fun getInfoFromAPI(view:View){

        //TODO:Borrar esto cuando se implemente la arquitectura mvvm text_home
        GlobalScope.launch(Dispatchers.Main) {

            try{

                val lblPersonalID = view.findViewById<TextView>(R.id.tvPersonalID)
                val lblNombres = view.findViewById<TextView>(R.id.tvNombresUsuario)
                val lblApellidos = view.findViewById<TextView>(R.id.tvApellidosUsuario)
                val lblNacionalidad = view.findViewById<TextView>(R.id.tvNacionalidad)
                val lblTipoUsuario = view.findViewById<TextView>(R.id.tvTipoUsuario)
                val txtEmail = view.findViewById<TextView>(R.id.txtEmailUsuario)
                val txtTelefono = view.findViewById<TextView>(R.id.txtTelefonoUsuario)
                val txtDireccion = view.findViewById<TextView>(R.id.txtDireccionUsuario)
                val usuapi = FeriaVirtualAPIProvider.provideUsuarioAPI()
                val usudata = usuapi.getUserInfo(3).await()

                lblPersonalID.text = usudata.usuario.personal_id
                lblNombres.text = usudata.usuario.nombre + " " + usudata.usuario.nombre_segundo
                lblApellidos.text = usudata.usuario.apellido_paterno + " " + usudata.usuario.apellido_materno
                lblNacionalidad.text = usudata.usuario.nacionalidad
                lblTipoUsuario.text = usudata.usuario.rol
                txtEmail.text = usudata.usuario.email
                txtTelefono.text = usudata.usuario.telefono.toString()
                txtDireccion.text = usudata.usuario.direccion


            } catch(e:Exception){

                Snackbar.make(view,"Ocurrió un error! Revisa el Logcat!",Snackbar.LENGTH_LONG).show()
                Log.i("MY_PROFILE_FRAGMENT","Error: ${ if(e?.message != null) e.message else  "Desconocido"    }")

            }


        }


    }
}