package com.feriantes4dawin.feriavirtual.dependencies

import android.content.Context
import com.feriantes4dawin.feriavirtual.data.db.FeriaVirtualDatabase

object FeriaVirtualDBProvider {

    fun provideDB(c:Context):FeriaVirtualDatabase{

        return FeriaVirtualDatabase.getInstance(c)

    }


}