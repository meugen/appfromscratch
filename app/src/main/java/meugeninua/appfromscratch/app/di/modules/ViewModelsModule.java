package meugeninua.appfromscratch.app.di.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import meugeninua.appfromscratch.app.di.keys.ViewModelKey;
import meugeninua.appfromscratch.ui.activities.ViewModelFactoryImpl;
import meugeninua.appfromscratch.ui.activities.main.fragment.main.vm.MainViewModel;

@Module
public interface ViewModelsModule {

    @Binds @Singleton
    ViewModelProvider.Factory bindViewModelFactory(ViewModelFactoryImpl impl);

    @Binds @IntoMap @ViewModelKey(MainViewModel.class)
    ViewModel bindMainViewModel(MainViewModel viewModel);
}
