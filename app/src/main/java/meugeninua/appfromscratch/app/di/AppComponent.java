package meugeninua.appfromscratch.app.di;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import meugeninua.appfromscratch.app.TheApp;
import meugeninua.appfromscratch.app.di.modules.AppModule;
import meugeninua.appfromscratch.app.di.modules.ComponentsModule;
import meugeninua.appfromscratch.app.di.modules.ViewModelsModule;
import meugeninua.appfromscratch.app.di.scopes.PerApplication;

@Component(modules = {/*AndroidSupportInjectionModule.class,*/
        AppModule.class, ComponentsModule.class, ViewModelsModule.class})
@PerApplication
public interface AppComponent extends AndroidInjector<TheApp> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<TheApp> {}
}
