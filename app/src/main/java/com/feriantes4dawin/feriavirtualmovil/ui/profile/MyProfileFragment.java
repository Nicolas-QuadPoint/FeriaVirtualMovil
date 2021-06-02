package com.feriantes4dawin.feriavirtualmovil.ui.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.feriantes4dawin.feriavirtualmovil.FeriaVirtualApplication;
import com.feriantes4dawin.feriavirtualmovil.FeriaVirtualComponent;
import com.feriantes4dawin.feriavirtualmovil.R;
import com.feriantes4dawin.feriavirtualmovil.data.models.ObjetoModificacionContrasena;
import com.feriantes4dawin.feriavirtualmovil.data.models.Usuario;
import com.feriantes4dawin.feriavirtualmovil.data.repos.UsuarioRepositoryImpl;
import com.feriantes4dawin.feriavirtualmovil.ui.util.FeriaVirtualConstants;
import com.feriantes4dawin.feriavirtualmovil.ui.util.SimpleAction;
import com.feriantes4dawin.feriavirtualmovil.ui.util.UtilityFunctions;
import com.feriantes4dawin.feriavirtualmovil.ui.widgets.ChangePasswordDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import javax.inject.Inject;

public class MyProfileFragment extends Fragment {

    //override val di by closestDI()

    //private val myProfileViewModelFactory by instance<MyProfileViewModelFactory>()
    private MyProfileViewModelFactory myProfileViewModelFactory;

    private MyProfileViewModel myProfileViewModel;

    private FeriaVirtualComponent feriaVirtualComponent;

    @Inject
    public UsuarioRepositoryImpl usuarioRepository;

    @Inject
    public Gson convertidorJSON;


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

        Button btnCambiarPasswd = (Button) view.findViewById(R.id.fmp_btnCambiarContrasena);
        Button btnGuardarCambios = (Button) view.findViewById(R.id.fmp_btnGuardar);
        Button btnCancelarCambios = (Button) view.findViewById(R.id.fmp_btnDeshacer);
        ImageButton btnImagen = (ImageButton) view.findViewById(R.id.fmp_btnFotoPerfilUsuario);
        SwipeRefreshLayout miSwiper = (SwipeRefreshLayout)view.findViewById(R.id.fmp_swipeFMP);

        //Asignamos eventos a los controles
        btnCambiarPasswd.setOnClickListener(v -> {
                btnCambiarContrasenaClick(v);
            }
        );

        btnGuardarCambios.setOnClickListener(v -> {
                btnGuardarCambiosPerfilClick(v);
            }
        );

        btnCancelarCambios.setOnClickListener(v -> {
                btnCancelarCambiosPerfilClick(v);
            }
        );

        btnImagen.setOnClickListener(v -> {
                btnFotoPerfilUsuarioClick(v);
            }
        );

        miSwiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                View pantallaCarga = getActivity().findViewById(R.id.dcp_llloading);
                pantallaCarga.setVisibility(View.VISIBLE);

                myProfileViewModel.getDatosUsuario();

            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Creation of the login graph using the application graph
        feriaVirtualComponent = ((FeriaVirtualApplication) getActivity().getApplicationContext())
                .getFeriaVirtualComponent();

        // Make Dagger instantiate @Inject fields in LoginActivity
        feriaVirtualComponent.injectUsuarioRepositoryIntoMyProfileFragment(this);

        //Creamos nuestro viewmodel
        this.myProfileViewModelFactory = new MyProfileViewModelFactory(
                usuarioRepository,
                convertidorJSON,
                (FeriaVirtualApplication) requireActivity().getApplicationContext()
        );

        this.myProfileViewModel = new ViewModelProvider(this,myProfileViewModelFactory).
                get(MyProfileViewModel.class);

        //Observamos el livedata del objeto y veamos que pasa!
        myProfileViewModel.getDatosUsuario().observe(this,
            new Observer<Usuario>() {
                @Override
                public void onChanged(Usuario usuario) {

                    actualizarDatosVista(usuario);

                }
            }
        );
    }


    private void actualizarDatosVista(Usuario usuario){

        FragmentActivity fa = getActivity();

        View pantallaCarga = fa.findViewById(R.id.dcp_llloading);
        SwipeRefreshLayout miSwiper = fa.findViewById(R.id.fmp_swipeFMP);

        if(usuario != null) {


            TextView lblPersonalID = fa.findViewById(R.id.fmp_lblPersonalID);
            TextView lblNombres = fa.findViewById(R.id.fmp_lblNombresUsuario);
            TextView lblApellidos = fa.findViewById(R.id.fmp_lblApellidosUsuario);
            TextView lblNacionalidad = fa.findViewById(R.id.fmp_lblNacionalidad);
            TextView lblTipoUsuario = fa.findViewById(R.id.fmp_lblTipoUsuario);
            TextView txtEmail = fa.findViewById(R.id.fmp_lblEmailUsuario);
            EditText txtTelefono = fa.findViewById(R.id.fmp_txtTelefonoUsuario);
            EditText txtDireccion = fa.findViewById(R.id.fmp_txtDireccionUsuario);

            lblPersonalID.setText(  usuario.personal_id  );

            lblNombres.setText(
                String.format("%s%s",
                    usuario.nombre,
                    usuario.nombre_segundo != null?
                        ", " + usuario.nombre_segundo :
                        ""
                )
            );

            lblApellidos.setText(
                    String.format("%s%s",
                            usuario.apellido_paterno,
                            usuario.apellido_materno != null?
                                ", " + usuario.apellido_materno :
                                ""
                    )
            );

            lblNacionalidad.setText( usuario.nacionalidad.nombre );

            lblTipoUsuario.setText( usuario.rol.descripcion );

            txtEmail.setText( usuario.email );

            txtDireccion.setText( usuario.direccion );

            txtTelefono.setText( String.valueOf(usuario.telefono) );


        } else {

            Snackbar.make(miSwiper,R.string.err_msg_error_getting_user_info,Snackbar.LENGTH_LONG).show();

        }

        pantallaCarga.setVisibility(View.GONE);

        if(miSwiper.isRefreshing()){
            miSwiper.setRefreshing(false);
        }
    }

    public void btnCambiarContrasenaClick(View v){

        try {

            ChangePasswordDialog cpd = new ChangePasswordDialog(
                    (AppCompatActivity) this.getActivity(),
                    new SimpleAction() {
                        @Override
                        public void doAction(Object o) {

                            try {

                                View contenedorDialogo = (View)o;
                                String usuarioString;
                                Usuario u;

                                LinearLayout pantallaCargaDialogo = contenedorDialogo.findViewById(R.id.dcp_llloading);
                                EditText txtPasswd = contenedorDialogo.findViewById(R.id.dcp_txtPasswd2);
                                ObjetoModificacionContrasena emc = new ObjetoModificacionContrasena();
                                SharedPreferences sp = getActivity().getSharedPreferences(
                                        FeriaVirtualConstants.FERIAVIRTUAL_MOVIL_SHARED_PREFERENCES,
                                        Context.MODE_PRIVATE
                                );

                                usuarioString = sp.getString(FeriaVirtualConstants.SP_USUARIO_OBJ_STR,"");
                                u = convertidorJSON.fromJson(usuarioString,Usuario.class);

                                emc.id_usuario = u.id_usuario;
                                emc.contrasena_actual = "none";
                                emc.nueva_contrasena = txtPasswd.getText().toString();

                                myProfileViewModel.cambiarContrasena(emc,new SimpleAction(){

                                            @Override
                                            public void doAction(Object o) {

                                                Boolean resultado = (Boolean)o;

                                                pantallaCargaDialogo.setVisibility(View.GONE);

                                                Snackbar.make(
                                                        getActivity().findViewById(android.R.id.content),
                                                        resultado? R.string.mpf_setting_passwd_done : R.string.err_msg_generic,
                                                        Snackbar.LENGTH_LONG
                                                ).show();

                                            }
                                        }
                                );


                            } catch(Exception ex){

                                Snackbar.make(
                                        getActivity().findViewById(android.R.id.content),
                                        R.string.err_msg_generic,
                                        Snackbar.LENGTH_LONG
                                ).show();

                            }


                        }
                    },
                    null
                );

            //Mostramos el dialogo
             cpd.generate().show();

            //Cerramos el dialogo
            //d.cancel()

        } catch (Exception ex){

            Log.e("FRAGMENT_MY_PROFILE",ex.toString());
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

}