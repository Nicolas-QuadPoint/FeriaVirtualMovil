package com.feriantes4dawin.feriavirtualmovil;

import com.feriantes4dawin.feriavirtualmovil.data.datasources.local.FeriaVirtualDBProvider;
import com.feriantes4dawin.feriavirtualmovil.data.datasources.remote.FeriaVirtualAPIProvider;
import com.feriantes4dawin.feriavirtualmovil.data.repos.SubastaRepository;
import com.feriantes4dawin.feriavirtualmovil.data.repos.SubastaRepositoryImpl;
import com.feriantes4dawin.feriavirtualmovil.data.repos.UsuarioRepository;
import com.feriantes4dawin.feriavirtualmovil.data.repos.UsuarioRepositoryImpl;
import com.feriantes4dawin.feriavirtualmovil.data.repos.VentaRepository;
import com.feriantes4dawin.feriavirtualmovil.data.repos.VentaRepositoryImpl;
import com.feriantes4dawin.feriavirtualmovil.ui.home.HomeFragment;
import com.feriantes4dawin.feriavirtualmovil.ui.login.LoginActivity;
import com.feriantes4dawin.feriavirtualmovil.ui.main.MainActivity;
import com.feriantes4dawin.feriavirtualmovil.ui.proccesses.MyProcessesFragment;
import com.feriantes4dawin.feriavirtualmovil.ui.proccesses.MyProcessesViewModel;
import com.feriantes4dawin.feriavirtualmovil.ui.profile.MyProfileFragment;
import com.feriantes4dawin.feriavirtualmovil.ui.sales.CurrentSalesFragment;
import com.feriantes4dawin.feriavirtualmovil.ui.sales.SaleDetailActivity;
import com.feriantes4dawin.feriavirtualmovil.ui.util.JsonConverterProvider;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(
    modules={
        FeriaVirtualApplicationModule.class,
        JsonConverterProvider.class,
        FeriaVirtualAPIProvider.class,
        FeriaVirtualDBProvider.class,
        UsuarioRepositoryImpl.class,
        VentaRepositoryImpl.class,
        SubastaRepositoryImpl.class
})
public interface FeriaVirtualComponent {

    void injectUsuarioRepositoryIntoMyProfileFragment(MyProfileFragment myProfileFragment);
    void injectVentaRepositoryIntoCurrentSalesFragment(CurrentSalesFragment currentSalesFragment);
    void injectVentaRepositoryIntoMyProcessesFragment(MyProcessesFragment MyProcessesFragment);
    void injectVentaRepositoryIntoSaleDetailActivity(SaleDetailActivity saleDetailActivity);
    void injectSubastaRepositoryIntoSaleDetailActivity(SaleDetailActivity saleDetailActivity);
    void injectUsuarioRepositoryIntoLoginActivity(LoginActivity loginActivity);
    void injectUsuarioRepositoryIntoMainActivity(MainActivity mainActivity);

    void injectIntoHomeFragment(HomeFragment homeFragment);

}
