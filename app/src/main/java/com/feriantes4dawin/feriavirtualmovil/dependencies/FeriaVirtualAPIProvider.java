package com.feriantes4dawin.feriavirtualmovil.dependencies;

import com.feriantes4dawin.feriavirtualmovil.data.services.UsuarioAPIService;
import com.feriantes4dawin.feriavirtualmovil.ui.util.FeriaVirtualConstants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FeriaVirtualAPIProvider {

    public static UsuarioAPIService provideUsuarioAPI() {

        return commonAPIBuilder().build().
        create(UsuarioAPIService.class);

    }

    private static Retrofit.Builder commonAPIBuilder(){

        OkHttpClient httpClient = new OkHttpClient.Builder().build();

        //Crea un builder para transformar los datos a enviar a la api
        return new Retrofit.Builder().

            //Se establece el objeto que gestionara las peticiones http para retrofit
        client(httpClient).

            //Esto indica la url base que la api tiene.
        baseUrl(FeriaVirtualConstants.URL_BASE_API_WEB_FERIAVIRTUAL).

            //Esto activa el mecanismo de coroutines de Kotlin, usando un adapter que
            //gestiona dichos procesos
        //addCallAdapterFactory(CoroutineCallAdapterFactory()).

            //Y e aquí lo más importante: El objeto que se encargará de convertir
            //cada objeto de dato en JSON para nuestra api
        addConverterFactory(GsonConverterFactory.create());
    }

}