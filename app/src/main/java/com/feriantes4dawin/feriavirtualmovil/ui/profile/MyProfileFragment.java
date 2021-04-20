package com.feriantes4dawin.feriavirtualmovil.ui.profile;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.feriantes4dawin.feriavirtualmovil.R;
import com.feriantes4dawin.feriavirtualmovil.data.db.FeriaVirtualDatabase;
import com.feriantes4dawin.feriavirtualmovil.data.network.UsuarioDataSourceImpl;
import com.feriantes4dawin.feriavirtualmovil.data.repos.UsuarioRepositoryImpl;
import com.feriantes4dawin.feriavirtualmovil.dependencies.FeriaVirtualAPIProvider;
import com.feriantes4dawin.feriavirtualmovil.ui.util.SimpleAction;
import com.feriantes4dawin.feriavirtualmovil.ui.util.UtilityFunctions;
import com.feriantes4dawin.feriavirtualmovil.ui.widgets.ChangePasswordDialog;
import com.google.android.material.snackbar.Snackbar;

public class MyProfileFragment extends Fragment {

    //override val di by closestDI()

    //private val myProfileViewModelFactory by instance<MyProfileViewModelFactory>()
    private MyProfileViewModelFactory myProfileViewModelFactory;

    private MyProfileViewModel myProfileViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //Obtenermos la vista con todos los controles
        View root = inflater.inflate(R.layout.fragment_my_profile, container, false);

        //Retornamos la vista para que sea dibujada
        return root;
    }

    @Override
    public void onViewCreated(View view ,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnCambiarPasswd = (Button) view.findViewById(R.id.btnCambiarContrasena);
        Button btnGuardarCambios = (Button) view.findViewById(R.id.btnGuardarCambiosPerfil);
        Button btnCancelarCambios = (Button) view.findViewById(R.id.btnCancelarCambiosPerfil);
        ImageButton btnImagen = (ImageButton) view.findViewById(R.id.btnFotoPerfilUsuario);
        SwipeRefreshLayout miSwiper = (SwipeRefreshLayout)view.findViewById(R.id.swipeMyProfile);

        //Asignamos eventos a los controles
        btnCambiarPasswd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                btnCambiarContrasenaClick(v);
            }
        });

        btnGuardarCambios.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                btnGuardarCambiosPerfilClick(v);
            }
        });

        btnCancelarCambios.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                btnCancelarCambiosPerfilClick(v);
            }
        });

        btnImagen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                btnFotoPerfilUsuarioClick(v);
            }
        });

        miSwiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getInfoFromAPI(view);
                miSwiper.setRefreshing(false);

            }
        });


        //TODO:Borrar esto cuando se implemente la arquitectura mvvm text_home
        getInfoFromAPI(view);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.myProfileViewModelFactory  = new MyProfileViewModelFactory(
            new UsuarioRepositoryImpl(
                (FeriaVirtualDatabase.getInstance(context)).usuarioDAO(),
                new UsuarioDataSourceImpl((FeriaVirtualAPIProvider.provideUsuarioAPI()))
            )
        );

        this.myProfileViewModel =  new ViewModelProvider(this,myProfileViewModelFactory)
                .get(MyProfileViewModel.class);

    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter,int nextAnim) {
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    public void btnCambiarContrasenaClick(View v){

        try {

            ChangePasswordDialog cpd = new ChangePasswordDialog(
                    (AppCompatActivity) this.getActivity(),
                    new SimpleAction() {
                        @Override
                        public void doAction(Object o) {
                            Snackbar.make(
                                requireView(),
                                getString(R.string.mpf_setting_passwd),
                                Snackbar.LENGTH_LONG
                            ).show();
                        }
                    },
                    null
                );

            //Mostramos el dialogo
             AlertDialog d = cpd.generate();

            //Abrimos el dialogo!
            d.show();

            //Extraimos la contraseña, y la modificamos!
            Log.i("[MY_PROFILE_FRAGMENT]",cpd.getPassword());

            //Cerramos el dialogo
            //d.cancel()

        } catch (Exception ex){

            Toast.makeText(this.getActivity(),R.string.err_msg_generic,Toast.LENGTH_SHORT).show();

        }

    }

    public void btnFotoPerfilUsuarioClick(View v){

        Toast.makeText(this.getActivity(),"Deberia cambiar imagen ¿No?",Toast.LENGTH_SHORT).show();
        UtilityFunctions.getImageFromGallery( ( (AppCompatActivity) this.getActivity() ));

    }

    public void btnGuardarCambiosPerfilClick(View v){

        Toast.makeText(this.getActivity(),"Deberia guardar cambios ¿No?",Toast.LENGTH_SHORT).show();

    }

    public void btnCancelarCambiosPerfilClick(View v){

        Toast.makeText(this.getActivity(),"Deberia cancelar cambios ¿No?",Toast.LENGTH_SHORT).show();

    }

    private void getInfoFromAPI(View v){

        //TODO:Borrar esto cuando se implemente la arquitectura mvvm text_home
        /*
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
        */
    }
}