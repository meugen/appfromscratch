package meugeninua.appfromscratch.app.di.modules;

import android.content.Context;

import dagger.Binds;
import dagger.Module;
import meugeninua.appfromscratch.app.TheApp;
import meugeninua.appfromscratch.app.di.qualifiers.AppContext;
import meugeninua.appfromscratch.app.di.scopes.PerApplication;

@Module
public interface AppModule {

    @Binds @AppContext @PerApplication
    Context bindAppContext(TheApp app);
}
