package com.feriantes4dawin.feriavirtual.ui.sales

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.feriantes4dawin.feriavirtual.R

class SaleDetailActivity : AppCompatActivity() {

    private val viewmodel = ViewModelProvider(this).get(SaleDetailViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sale_detail)
    }
}