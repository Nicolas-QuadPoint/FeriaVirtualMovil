package com.feriantes4dawin.feriavirtualmovil.ui.sales;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.feriantes4dawin.feriavirtualmovil.R;

public class CurrentSalesFragment extends Fragment {

    private CurrentSalesViewModel currentSalesViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        this.currentSalesViewModel = new ViewModelProvider(this).get(CurrentSalesViewModel.class);

        View root = inflater.inflate(R.layout.fragment_current_sales, container, false);
        RecyclerView miRecicler = (RecyclerView) root.findViewById(R.id.asd_rcListaProductos);
        SwipeRefreshLayout miSwiper = (SwipeRefreshLayout)root.findViewById(R.id.swipeCurrentSales);

        miRecicler.setAdapter(new SimpleSaleItemCustomAdapter());
        miRecicler.setLayoutManager(new LinearLayoutManager(requireContext()));

        miSwiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {

                miRecicler.setAdapter(new SimpleSaleItemCustomAdapter());
                miRecicler.setLayoutManager( new LinearLayoutManager(requireContext()));

                miSwiper.setRefreshing(false);

            }

        });

        //currentSalesViewModel.text.observe(viewLifecycleOwner {
        //    textView.text = it
        //})
        return root;
    }

}