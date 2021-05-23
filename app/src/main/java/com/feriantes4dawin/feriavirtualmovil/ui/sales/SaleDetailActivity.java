package com.feriantes4dawin.feriavirtualmovil.ui.sales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;
import com.feriantes4dawin.feriavirtualmovil.R;
import com.feriantes4dawin.feriavirtualmovil.ui.auction.AuctionSaleActivity;
import com.feriantes4dawin.feriavirtualmovil.ui.util.FeriaVirtualConstants;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import org.jetbrains.annotations.NotNull;

public class SaleDetailActivity extends AppCompatActivity {

    private SaleDetailViewModel viewmodel;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.viewmodel = new ViewModelProvider(this).get(SaleDetailViewModel.class);

        setContentView(R.layout.activity_sale_detail);

        ExtendedFloatingActionButton asd_btnParticiparSubasta = (ExtendedFloatingActionButton)findViewById(R.id.asd_btnParticiparSubasta);

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

}