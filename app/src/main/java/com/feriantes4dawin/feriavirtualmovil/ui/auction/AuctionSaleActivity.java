package com.feriantes4dawin.feriavirtualmovil.ui.auction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.feriantes4dawin.feriavirtualmovil.FeriaVirtualApplication;
import com.feriantes4dawin.feriavirtualmovil.FeriaVirtualComponent;
import com.feriantes4dawin.feriavirtualmovil.data.models.Rol;
import com.feriantes4dawin.feriavirtualmovil.data.models.Usuario;
import com.feriantes4dawin.feriavirtualmovil.ui.util.FeriaVirtualConstants;
import com.feriantes4dawin.feriavirtualmovil.ui.widgets.PushProductorDialog;
import com.feriantes4dawin.feriavirtualmovil.ui.widgets.PushTransportistaDialog;
import com.feriantes4dawin.feriavirtualmovil.ui.widgets.SimpleDialog;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.feriantes4dawin.feriavirtualmovil.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import javax.inject.Inject;

public class AuctionSaleActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    private FeriaVirtualComponent feriaVirtualComponent;

    @Inject
    public Gson convertidorJSON;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_auction_sale);

        // Creation of the login graph using the application graph
        feriaVirtualComponent = ((FeriaVirtualApplication) getApplicationContext())
                .getFeriaVirtualComponent();

        // Make Dagger instantiate @Inject fields in LoginActivity
        feriaVirtualComponent.injectIntoAuctionSaleActivity(this);

        Usuario usuario;
        ExtendedFloatingActionButton aas_btnPujar = (ExtendedFloatingActionButton) findViewById(R.id.aas_btnPujar);
        RecyclerView rv = (RecyclerView) findViewById(R.id.aas_rvListaPujas);
        SwipeRefreshLayout miSwiper = findViewById(R.id.aas_swipeSaleDetail);
        PushListCustomAdapter rvAdapter = new PushListCustomAdapter();
        rv.setAdapter(rvAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        SharedPreferences sp = getSharedPreferences(
                FeriaVirtualConstants.FERIAVIRTUAL_MOVIL_SHARED_PREFERENCES,
                Context.MODE_PRIVATE
        );

        try{

            String stringUsuario = sp.getString(FeriaVirtualConstants.SP_USUARIO_OBJ_STR,"");
            usuario = convertidorJSON.fromJson(stringUsuario,Usuario.class);

        }catch(Exception ex){

            Snackbar.make(this,findViewById(android.R.id.content),getString(R.string.err_msg_generic),Snackbar.LENGTH_SHORT).show();
            Log.e("AUCTION_SALE_ACTIVITY",ex.toString());
            usuario = null;
        }

        if(usuario != null && usuario.rol != null){

            SimpleDialog sd;

            if(Rol.PRODUCTOR.equals(usuario.rol)){

                sd = new PushProductorDialog(this,null,null);

            } else if (Rol.TRANSPORTISTA.equals(usuario.rol)){

                sd = new PushTransportistaDialog(this,null,null);

            } else {

                Snackbar.make(this,findViewById(android.R.id.content),getString(R.string.err_msg_generic),Snackbar.LENGTH_SHORT).show();
                sd = null;

            }

            if(sd != null){


                aas_btnPujar.setOnClickListener(v -> {

                   sd.generate().show();

                });

            } else {

                aas_btnPujar.setEnabled(false);

            }

        }

        miSwiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {

                PushListCustomAdapter nrvAdapter = new PushListCustomAdapter();

                rv.setAdapter(nrvAdapter);
                rv.setLayoutManager(new LinearLayoutManager(AuctionSaleActivity.this));

                miSwiper.setRefreshing(false);
            }

        });
    }

}