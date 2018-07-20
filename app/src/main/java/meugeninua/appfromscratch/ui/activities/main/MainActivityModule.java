package meugeninua.appfromscratch.ui.activities.main;

import android.support.v7.app.AppCompatActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import meugeninua.appfromscratch.ui.activities.base.BaseActivityModule;
import meugeninua.appfromscratch.ui.activities.main.fragment.main.MainFragment;
import meugeninua.appfromscratch.ui.activities.main.fragment.main.MainFragmentModule;

@Module(includes = BaseActivityModule.class)
public interface MainActivityModule {

    @Binds
    AppCompatActivity bindActivity(MainActivity activity);

    @ContributesAndroidInjector(modules = MainFragmentModule.class)
    MainFragment contributeMainFragment();
}
