package com.feriantes4dawin.feriavirtualmovil.ui.auction;

import android.os.Bundle;

import com.feriantes4dawin.feriavirtualmovil.ui.widgets.PushProductorBidDialog;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.feriantes4dawin.feriavirtualmovil.R;

public class AuctionSaleActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_auction_sale);

        ExtendedFloatingActionButton aas_btnPujar = (ExtendedFloatingActionButton) findViewById(R.id.aas_btnPujar);

        aas_btnPujar.setOnClickListener(v -> {

            PushProductorBidDialog dlg = new PushProductorBidDialog(AuctionSaleActivity.this,null,null);
            dlg.generate().show();

        });
    }

}