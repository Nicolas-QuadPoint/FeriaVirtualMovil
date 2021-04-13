package com.feriantes4dawin.feriavirtual.ui.util

interface ViewmodelFactory {

    fun <T>create(viewmodel:T) : T

}