package com.feriantes4dawin.feriavirtual

import android.app.Application
import org.kodein.di.DI
import org.kodein.di.DIAware

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
class FeriaVirtualApplication : Application(), DIAware {

    override val di = DI.lazy {

    }

    override fun onCreate() {
        super.onCreate()

    }

}