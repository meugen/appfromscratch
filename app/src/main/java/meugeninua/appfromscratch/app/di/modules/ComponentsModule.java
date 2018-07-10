package meugeninua.appfromscratch.app.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import meugeninua.appfromscratch.app.di.scopes.PerActivity;
import meugeninua.appfromscratch.ui.activities.main.MainActivity;
import meugeninua.appfromscratch.ui.activities.main.MainActivityModule;

@Module
public interface ComponentsModule {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    @PerActivity
    MainActivity contributeMainActivity();
}
