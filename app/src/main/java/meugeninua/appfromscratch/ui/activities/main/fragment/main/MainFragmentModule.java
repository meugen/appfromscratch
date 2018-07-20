package meugeninua.appfromscratch.ui.activities.main.fragment.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import meugeninua.appfromscratch.ui.activities.base.fragments.base.BaseFragmentModule;
import meugeninua.appfromscratch.ui.activities.base.fragments.base.state.ArchState;
import meugeninua.appfromscratch.ui.activities.base.fragments.base.state.ArchStateImpl;
import meugeninua.appfromscratch.ui.activities.main.fragment.main.binding.MainBinding;
import meugeninua.appfromscratch.ui.activities.main.fragment.main.binding.MainBindingImpl;
import meugeninua.appfromscratch.ui.activities.main.fragment.main.vm.MainViewModel;

@Module(includes = BaseFragmentModule.class)
public interface MainFragmentModule {

    @Binds
    Fragment bindFragment(MainFragment fragment);

    @Binds
    MainBinding bindBinding(MainBindingImpl impl);

    @Binds
    ArchState bindState(ArchStateImpl impl);

    @Provides
    static MainViewModel provideMainViewModel(
            final Fragment fragment,
            final ViewModelProvider.Factory factory) {
        return ViewModelProviders
                .of(fragment, factory)
                .get(MainViewModel.class);
    }
}
