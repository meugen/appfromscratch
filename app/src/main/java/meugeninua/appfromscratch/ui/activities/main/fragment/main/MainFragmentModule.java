package meugeninua.appfromscratch.ui.activities.main.fragment.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import meugeninua.appfromscratch.app.di.scopes.PerFragment;
import meugeninua.appfromscratch.ui.activities.base.fragments.base.BaseFragmentModule;
import meugeninua.appfromscratch.ui.activities.base.fragments.base.state.ArchState;
import meugeninua.appfromscratch.ui.activities.base.fragments.base.state.ArchStateImpl;
import meugeninua.appfromscratch.ui.activities.main.fragment.main.binding.MainBinding;
import meugeninua.appfromscratch.ui.activities.main.fragment.main.binding.MainBindingImpl;
import meugeninua.appfromscratch.ui.activities.main.fragment.main.vm.MainViewModel;

@Module(includes = BaseFragmentModule.class)
public interface MainFragmentModule {

    @Binds @PerFragment
    Fragment bindFragment(MainFragment fragment);

    @Binds @PerFragment
    MainBinding bindBinding(MainBindingImpl impl);

    @Binds @PerFragment
    ArchState bindState(ArchStateImpl impl);

    @Provides
    static MainViewModel provideMainViewModel(
            final AppCompatActivity activity,
            final ViewModelProvider.Factory factory) {
        return ViewModelProviders
                .of(activity, factory)
                .get(MainViewModel.class);
    }
}
