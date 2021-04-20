package com.feriantes4dawin.feriavirtualmovil.ui.proccesses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.feriantes4dawin.feriavirtual.R
import org.kodein.di.DI
import org.kodein.di.DIAware

class MyProcessesFragment : Fragment(), DIAware {

    override val di: DI by lazy { (requireActivity().applicationContext as DIAware).di }
    private lateinit var myProcessesViewModel: MyProcessesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        myProcessesViewModel =
                ViewModelProvider(this).get(MyProcessesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_my_processes, container, false)
        /*
        myProcessesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }

}