package com.feriantes4dawin.feriavirtualmovil.ui.sales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.feriantes4dawin.feriavirtualmovil.FeriaVirtualApplication;
import com.feriantes4dawin.feriavirtualmovil.FeriaVirtualComponent;
import com.feriantes4dawin.feriavirtualmovil.R;
import com.feriantes4dawin.feriavirtualmovil.data.models.Venta;
import com.feriantes4dawin.feriavirtualmovil.data.models.Ventas;
import com.feriantes4dawin.feriavirtualmovil.data.models.VentasSimples;
import com.feriantes4dawin.feriavirtualmovil.data.repos.VentaRepository;
import com.feriantes4dawin.feriavirtualmovil.data.repos.VentaRepositoryImpl;
import com.feriantes4dawin.feriavirtualmovil.ui.auction.AuctionSaleActivity;
import com.feriantes4dawin.feriavirtualmovil.ui.util.FeriaVirtualConstants;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class SaleDetailActivity extends AppCompatActivity {

    private SaleDetailViewModel saleDetailViewModel;
    private SaleDetailViewModelFactory saleDetailViewModelFactory;

    private FeriaVirtualComponent feriaVirtualComponent;

    private Integer id_venta;

    @Inject
    public VentaRepositoryImpl ventaRepository;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sale_detail);

        ExtendedFloatingActionButton asd_btnParticiparSubasta = (ExtendedFloatingActionButton)findViewById(R.id.asd_btnParticiparSubasta);
        SwipeRefreshLayout miSwiper = (SwipeRefreshLayout)findViewById(R.id.asd_swipeSaleDetail);
        SharedPreferences sp = getSharedPreferences(
                FeriaVirtualConstants.FERIAVIRTUAL_MOVIL_SHARED_PREFERENCES,
                Context.MODE_PRIVATE);

        // Creation of the login graph using the application graph
        feriaVirtualComponent = ((FeriaVirtualApplication)getApplicationContext())
                .getFeriaVirtualComponent();

        // Make Dagger instantiate @Inject fields in LoginActivity
        feriaVirtualComponent.injectVentaRepositoryIntoSaleDetailActivity(this);

        //Instanciamos el factory!
        this.saleDetailViewModelFactory = new SaleDetailViewModelFactory(
                ventaRepository,
                (FeriaVirtualApplication) getApplicationContext()
        );

        //Creamos nuestro viewmodel
        this.saleDetailViewModel = new ViewModelProvider(this,saleDetailViewModelFactory).
                get(SaleDetailViewModel.class);

        //Obtenemos el id de venta seleccionada en el fragmento CurrentSalesFragment o MyProcessesFragment
        this.id_venta = sp.getInt(FeriaVirtualConstants.SP_VENTA_ID,0);


        //Observamos el livedata del objeto y veamos que pasa!
        saleDetailViewModel.getDatosVenta(id_venta).observe(this,
                new Observer<Venta>() {
                    @Override
                    public void onChanged(Venta venta) {
                        rellenarDatosVenta(venta);

                    }
                }
        );

        miSwiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                saleDetailViewModel.getDatosVenta(id_venta);
            }
        });

        asd_btnParticiparSubasta.setOnClickListener( v -> {

            Intent i = new Intent(SaleDetailActivity.this, AuctionSaleActivity.class);
            i.putExtra("id_venta","1");
            startActivity(i);

        });

    }

    @Override
    public void onBackPressed() {
        //No domino la navegación entre actividades aún, mejor finalicemos este.
        finish();
    }

    @Override
    public void supportNavigateUpTo(@NonNull @NotNull Intent upIntent) {
        upIntent.putExtra("fragment",FeriaVirtualConstants.FRAGMENTO_LISTA_PETICIONES_VENTA);
        super.supportNavigateUpTo(upIntent);
        Log.i("SALE_DETAIL_ACTIVITY","Deberia ir a " + FeriaVirtualConstants.FRAGMENTO_LISTA_PETICIONES_VENTA);
    }

    private void rellenarDatosVenta(Venta venta){

        SwipeRefreshLayout miSwiper = (SwipeRefreshLayout)findViewById(R.id.asd_swipeSaleDetail);

        if(venta != null){

            TextView lblNombreEmpresa = findViewById(R.id.csi_lblNombreNegocio);
            TextView lblFechaInicioVenta = findViewById(R.id.csi_lblFechaVenta);
            TextView lblComentariosVenta = findViewById(R.id.csi_lblComentariosVenta);
            TextView lblEstadoVenta = findViewById(R.id.csi_lblEstadoVenta);
            RecyclerView rvProductosSeleccionados = findViewById(R.id.asd_rvListaProductosSolicitados);

            lblNombreEmpresa.setText( venta.usuario_autor.nombre );
            lblFechaInicioVenta.setText( venta.fecha_inicio_venta );
            lblComentariosVenta.setText( venta.comentarios_venta );
            lblEstadoVenta.setText( venta.estado_venta.descripcion );

            //Creo un nuevo objeto adapter, pasandole los datos!
            rvProductosSeleccionados.setAdapter(new ListItemDetailProductCustomAdapter(venta.productos_venta));
            rvProductosSeleccionados.setLayoutManager( new LinearLayoutManager(this));


        } else {

            Snackbar.make(this,findViewById(R.id.asd_contenedor),getString(R.string.err_msg_generic),Snackbar.LENGTH_LONG);

        }

        if(miSwiper.isRefreshing()){
            miSwiper.setRefreshing(false);
        }

    }

}