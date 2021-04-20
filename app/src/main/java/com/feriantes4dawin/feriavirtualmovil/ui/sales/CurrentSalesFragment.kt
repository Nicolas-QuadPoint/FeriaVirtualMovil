package com.feriantes4dawin.feriavirtualmovil.ui.sales

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.feriantes4dawin.feriavirtual.R

class CurrentSalesFragment : Fragment() {

    private lateinit var currentSalesViewModel: CurrentSalesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        currentSalesViewModel  =
            ViewModelProvider(this).get(CurrentSalesViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_current_sales, container, false)

        val miRecicler = root.findViewById<RecyclerView>(R.id.rvCurrentSales)
        val miSwiper = root.findViewById<SwipeRefreshLayout>(R.id.swipeCurrentSales)

        miRecicler.adapter = SalesListCustomAdapter()
        miRecicler.layoutManager = LinearLayoutManager(requireContext())

        miSwiper.setOnRefreshListener {

            miRecicler.adapter = SalesListCustomAdapter()
            miRecicler.layoutManager = LinearLayoutManager(this@CurrentSalesFragment.requireContext())

            miSwiper.isRefreshing = false
        }

        //currentSalesViewModel.text.observe(viewLifecycleOwner {
        //    textView.text = it
        //})
        return root
    }

}