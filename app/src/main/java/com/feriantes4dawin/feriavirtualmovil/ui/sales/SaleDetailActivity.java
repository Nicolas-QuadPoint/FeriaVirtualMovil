package com.feriantes4dawin.feriavirtualmovil.ui.sales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import com.feriantes4dawin.feriavirtualmovil.R;
import com.feriantes4dawin.feriavirtualmovil.ui.auction.AuctionSaleActivity;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

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
}