package com.feriantes4dawin.feriavirtualmovil.ui.profile;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.feriantes4dawin.feriavirtualmovil.R;
import com.feriantes4dawin.feriavirtualmovil.data.models.Usuario;
import com.feriantes4dawin.feriavirtualmovil.data.network.responses.UsuarioResponse;
import com.feriantes4dawin.feriavirtualmovil.data.services.UsuarioAPIService;
import com.feriantes4dawin.feriavirtualmovil.dependencies.FeriaVirtualAPIProvider;
import com.feriantes4dawin.feriavirtualmovil.ui.util.SimpleAction;
import com.feriantes4dawin.feriavirtualmovil.ui.util.UtilityFunctions;
import com.feriantes4dawin.feriavirtualmovil.ui.widgets.ChangePasswordDialog;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.FutureTask;

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
        /*
        this.myProfileViewModelFactory  = new MyProfileViewModelFactory(

                new UsuarioRepositoryImpl(
                (FeriaVirtualDatabase.getInstance(context)).usuarioDAO(),
                new UsuarioDataSourceImpl((FeriaVirtualAPIProvider.provideUsuarioAPI()))
            )

        );

        this.myProfileViewModel =  new ViewModelProvider(this,myProfileViewModelFactory)
                .get(MyProfileViewModel.class);
        */

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
                                R.string.mpf_setting_passwd_done,
                                Snackbar.LENGTH_LONG
                            ).show();
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

    private void getInfoFromAPI(View view){

        //TODO:Borrar esto cuando se implemente la arquitectura mvvm text_home

        TextView lblPersonalID = view.findViewById(R.id.fmp_lblPersonalID);
        TextView lblNombres = view.findViewById(R.id.fmp_lblNombresUsuario);
        TextView lblApellidos = view.findViewById(R.id.fmp_lblApellidosUsuario);
        TextView lblNacionalidad = view.findViewById(R.id.fmp_lblNacionalidad);
        TextView lblTipoUsuario = view.findViewById(R.id.fmp_lblTipoUsuario);
        TextView txtEmail = view.findViewById(R.id.fmp_lblEmailUsuario);
        EditText txtTelefono = view.findViewById(R.id.fmp_txtTelefonoUsuario);
        EditText txtDireccion = view.findViewById(R.id.fmp_txtDireccionUsuario);

        FutureTask<Usuario> tarea = new FutureTask<Usuario>(() -> {

            try{


                UsuarioAPIService usuapi = FeriaVirtualAPIProvider.provideUsuarioAPI();
                UsuarioResponse usudata = usuapi.getUserInfo(21);

                if(usudata != null){
                    return usudata.usuario;
                } else {
                    return null;
                }

            } catch(Exception e){

                Snackbar.make(view,"Ocurrió un error! Revisa el Logcat!",Snackbar.LENGTH_LONG).show();
                Log.i("MY_PROFILE_FRAGMENT",String.format("Ups, un error... %s",e != null? e.getMessage() : "Desconocido!!"));

            }

            return null;
        });

        try{

            tarea.run();

            Usuario u = tarea.get();

            lblPersonalID.setText(u.personal_id);
            lblNombres.setText(u.nombre + " " + u.nombre_segundo);
            lblApellidos.setText(u.apellido_paterno + " " + u.apellido_materno);
            lblNacionalidad.setText(u.nacionalidad.toString());
            lblTipoUsuario.setText(u.rol.toString());
            txtEmail.setText(u.email);
            txtTelefono.setText(u.telefono.toString());
            txtDireccion.setText(u.direccion);



        }catch(Exception e){

            Snackbar.make(view,"Ocurrió un error! Revisa el Logcat!",Snackbar.LENGTH_LONG).show();
            Log.i("MY_PROFILE_FRAGMENT",String.format("Ups, un error... %s",e != null? e.getMessage() : "Desconocido!!"));

        }
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