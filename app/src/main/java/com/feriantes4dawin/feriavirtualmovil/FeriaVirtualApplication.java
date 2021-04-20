package com.feriantes4dawin.feriavirtualmovil;

import android.app.Application;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.kodein.di.DI;
import org.kodein.di.DIAware;
import org.kodein.di.DIContext;
import org.kodein.di.DITrigger;

/**
 * Esta clase se encargará de 'mantener vivas'
 * todas las referencias a objetos que deben inyectarse
 * en la aplicacion, por eso hereda de Application.
 *
 * Hilt manejará esto de forma oculta, sin que nosotros
 * tengamos que ocuparnos de esto.
 *
 * Nuestro único deber es indicar a Hilt 'donde'
 * inyectar las dependencias.
 */
public final class FeriaVirtualApplication extends Application implements  DIAware {

    public FeriaVirtualApplication(){
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @NotNull
    @Override
    public DI getDi() {
        return null;
    }

    @NotNull
    @Override
    public DIContext<?> getDiContext() {
        return null;
    }

    @Nullable
    @Override
    public DITrigger getDiTrigger() {
        return null;
    }
}