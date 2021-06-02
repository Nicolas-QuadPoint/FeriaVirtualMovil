package com.feriantes4dawin.feriavirtualmovil.ui.auction;

import android.os.Bundle;

import com.feriantes4dawin.feriavirtualmovil.ui.widgets.PushProductorDialog;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.feriantes4dawin.feriavirtualmovil.R;

public class AuctionSaleActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_auction_sale);

        ExtendedFloatingActionButton aas_btnPujar = (ExtendedFloatingActionButton) findViewById(R.id.aas_btnPujar);
        RecyclerView rv = (RecyclerView) findViewById(R.id.aas_rvListaPujas);
        SwipeRefreshLayout miSwiper = findViewById(R.id.aas_swipeSaleDetail);
        PushListCustomAdapter rvAdapter = new PushListCustomAdapter();

        rv.setAdapter(rvAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        aas_btnPujar.setOnClickListener(v -> {

            PushProductorDialog dlg = new PushProductorDialog(AuctionSaleActivity.this,o -> {

                rvAdapter.pushItem("New pusher");

            },null);
            dlg.generate().show();

        });

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