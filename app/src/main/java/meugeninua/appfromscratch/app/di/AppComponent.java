package meugeninua.appfromscratch.app.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import meugeninua.appfromscratch.app.TheApp;
import meugeninua.appfromscratch.app.di.modules.AppModule;
import meugeninua.appfromscratch.app.di.modules.ComponentsModule;
import meugeninua.appfromscratch.app.di.modules.ViewModelsModule;

@Component(modules = {/*AndroidSupportInjectionModule.class,*/
        AppModule.class, ComponentsModule.class, ViewModelsModule.class})
@Singleton
public interface AppComponent extends AndroidInjector<TheApp> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<TheApp> {}
}
