package com.feriantes4dawin.feriavirtualmovil.ui.proccesses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.feriantes4dawin.feriavirtualmovil.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MyProcessesFragment extends Fragment{

    private MyProcessesViewModel myProcessesViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){

        this.myProcessesViewModel = new ViewModelProvider(this).get(MyProcessesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_my_processes, container, false);
        /*
        myProcessesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root;
    }

}