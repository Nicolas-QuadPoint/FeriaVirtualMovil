package com.feriantes4dawin.feriavirtualmovil.dependencies;

import android.content.Context;
import com.feriantes4dawin.feriavirtualmovil.data.db.FeriaVirtualDatabase;
import com.feriantes4dawin.feriavirtualmovil.data.db.FeriaVirtualDatabase;

public class FeriaVirtualDBProvider {

    public static FeriaVirtualDatabase provideDB(Context c){

        return FeriaVirtualDatabase.getInstance(c);

    }


}