package com.feriantes4dawin.feriavirtual.ui.proccesses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyProcessesViewModel constructor() : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is proccesses Fragment"
    }
    val text: LiveData<String> = _text
}