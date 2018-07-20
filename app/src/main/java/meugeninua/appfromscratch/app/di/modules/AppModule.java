package meugeninua.appfromscratch.app.di.modules;

import android.content.Context;

import dagger.Binds;
import dagger.Module;
import meugeninua.appfromscratch.app.TheApp;
import meugeninua.appfromscratch.app.di.qualifiers.AppContext;

@Module
public interface AppModule {

    @Binds @AppContext
    Context bindAppContext(TheApp app);
}
