package com.feriantes4dawin.feriavirtualmovil.ui.sales;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.feriantes4dawin.feriavirtualmovil.FeriaVirtualApplication;
import com.feriantes4dawin.feriavirtualmovil.FeriaVirtualComponent;
import com.feriantes4dawin.feriavirtualmovil.R;
import com.feriantes4dawin.feriavirtualmovil.data.models.VentasSimples;
import com.feriantes4dawin.feriavirtualmovil.data.repos.VentaRepositoryImpl;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class CurrentSalesFragment extends Fragment {

    private CurrentSalesViewModel currentSalesViewModel;

    private CurrentSalesViewModelFactory currentSalesViewModelFactory;

    private FeriaVirtualComponent feriaVirtualComponent;

    @Inject
    public VentaRepositoryImpl ventaRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View root = inflater.inflate(R.layout.fragment_current_sales, container, false);
        SwipeRefreshLayout miSwiper = (SwipeRefreshLayout)root.findViewById(R.id.csf_swipeCurrentSales);

        miSwiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {

                //Reinicio la lista de ventas!
                currentSalesViewModel.getDatosVenta();

            }

        });

        //Activamos el refresco ahora, para que se sepa se que esta cargando algo!
        miSwiper.setRefreshing(true);

        return root;
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {

        super.onAttach(context);

        // Creation of the login graph using the application graph
        feriaVirtualComponent = ((FeriaVirtualApplication) getActivity().getApplicationContext())
                .getFeriaVirtualComponent();

        // Make Dagger instantiate @Inject fields in LoginActivity
        feriaVirtualComponent.injectVentaRepositoryIntoCurrentSalesFragment(this);

        //Instanciamos el factory!
        this.currentSalesViewModelFactory = new CurrentSalesViewModelFactory(ventaRepository,
                ((FeriaVirtualApplication) getActivity().getApplicationContext()));

        //Creamos nuestro viewmodel
        this.currentSalesViewModel = new ViewModelProvider(this,currentSalesViewModelFactory).
                get(CurrentSalesViewModel.class);


        //Observamos el livedata del objeto y veamos que pasa!
        currentSalesViewModel.getDatosVenta().observe(this,
                new Observer<VentasSimples>() {
                    @Override
                    public void onChanged(VentasSimples ventasSimples) {
                        Log.i("CUR_SALES_FRAGMENT","Paso por aquí!");
                        rellenarListaVentas(ventasSimples);

                    }
                }
        );

    }

    private void rellenarListaVentas(VentasSimples ventasSimples){

        View vistaMaestra = requireView();
        RecyclerView rvListaVentasSimples = vistaMaestra.findViewById(R.id.asd_rvListaProductosSolicitados);
        SwipeRefreshLayout miSwiper = vistaMaestra.findViewById(R.id.csf_swipeCurrentSales);
        View llPlaceholderEmptyList = vistaMaestra.findViewById(R.id.csf_llPlaceholderEmptyList);

        if(ventasSimples != null){

            //Limpio la lista anterior
            rvListaVentasSimples.setAdapter(null);

            //Creo un nuevo objeto adapter, pasandole los datos!
            rvListaVentasSimples.setAdapter(new SimpleSaleItemCustomAdapter(ventasSimples));
            rvListaVentasSimples.setLayoutManager( new LinearLayoutManager(requireContext()));

            llPlaceholderEmptyList.setVisibility(View.GONE);
            rvListaVentasSimples.setVisibility(View.VISIBLE);

        } else {

            llPlaceholderEmptyList.setVisibility(View.VISIBLE);
            rvListaVentasSimples.setVisibility(View.GONE);

        }

        //Desactivo el efecto de refresco si está activo
        if(miSwiper.isRefreshing()){
            miSwiper.setRefreshing(false);
        }

    }
}