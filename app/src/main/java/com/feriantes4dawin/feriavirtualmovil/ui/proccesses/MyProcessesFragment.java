package com.feriantes4dawin.feriavirtualmovil.ui.proccesses;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.feriantes4dawin.feriavirtualmovil.FeriaVirtualApplication;
import com.feriantes4dawin.feriavirtualmovil.FeriaVirtualComponent;
import com.feriantes4dawin.feriavirtualmovil.R;
import com.feriantes4dawin.feriavirtualmovil.data.models.VentasSimples;
import com.feriantes4dawin.feriavirtualmovil.data.repos.VentaRepositoryImpl;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class MyProcessesFragment extends Fragment{

    private MyProcessesViewModelFactory myProcessesViewModelFactory;
    private MyProcessesViewModel myProcessesViewModel;

    private FeriaVirtualComponent feriaVirtualComponent;

    @Inject
    public VentaRepositoryImpl ventaRepository;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){

        View root = inflater.inflate(R.layout.fragment_my_processes, container, false);
        SwipeRefreshLayout miSwiper = root.findViewById(R.id.fmproc_swipeMyProcesses);

        miSwiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                myProcessesViewModel.getDatosVenta();

            }
        });

        return root;
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {

        super.onAttach(context);

        // Creation of the login graph using the application graph
        feriaVirtualComponent = ((FeriaVirtualApplication) getActivity().getApplicationContext())
                .getFeriaVirtualComponent();

        // Make Dagger instantiate @Inject fields in LoginActivity
        feriaVirtualComponent.injectVentaRepositoryIntoMyProcessesFragment(this);

        //Creamos nuestro viewmodel
        this.myProcessesViewModelFactory = new MyProcessesViewModelFactory(
                ventaRepository,
                (FeriaVirtualApplication) requireActivity().getApplicationContext()
        );

        this.myProcessesViewModel = new ViewModelProvider(this,myProcessesViewModelFactory).
                get(MyProcessesViewModel.class);

        //Observamos el livedata del objeto y veamos que pasa!
        myProcessesViewModel.getDatosVenta().observe(this,
                new Observer<VentasSimples>() {
                    @Override
                    public void onChanged(VentasSimples ventasSimples) {

                        actualizarDatosVista(ventasSimples);

                    }
                }
        );


    }

    public void actualizarDatosVista(VentasSimples ventasSimples){

        SwipeRefreshLayout miSwiper = requireView().findViewById(R.id.fmproc_swipeMyProcesses);
        View llPlaceholderEmptyList = requireView().findViewById(R.id.myprocf_llPlaceholderEmptyList);
        View listaElementos = requireView().findViewById(R.id.myprocf_listaElementos);
        RecyclerView rvMyProcesses = requireView().findViewById(R.id.rvMyProcesses);

        if(ventasSimples != null){

            listaElementos.setVisibility(View.VISIBLE);
            llPlaceholderEmptyList.setVisibility(View.GONE);

        } else {

            listaElementos.setVisibility(View.GONE);
            llPlaceholderEmptyList.setVisibility(View.VISIBLE);

        }


        if(miSwiper.isRefreshing()){
            miSwiper.setRefreshing(false);
        }
    }
}