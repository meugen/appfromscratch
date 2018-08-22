package meugeninua.appfromscratch.app.di.modules

import android.content.Context

import dagger.Binds
import dagger.Module
import meugeninua.appfromscratch.app.TheApp
import meugeninua.appfromscratch.app.di.qualifiers.AppContext

@Module
interface AppModule {

    @Binds @AppContext
    fun bindAppContext(app: TheApp): Context
}
