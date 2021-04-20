package com.feriantes4dawin.feriavirtualmovil.ui.sales;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import com.feriantes4dawin.feriavirtualmovil.R;

public class SaleDetailActivity extends AppCompatActivity {

    private SaleDetailViewModel viewmodel;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.viewmodel = new ViewModelProvider(this).get(SaleDetailViewModel.class);

        setContentView(R.layout.activity_sale_detail);

    }
}