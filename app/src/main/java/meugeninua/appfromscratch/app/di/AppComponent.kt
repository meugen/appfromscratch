package meugeninua.appfromscratch.app.di

import javax.inject.Singleton

import dagger.Component
import dagger.android.AndroidInjector
import meugeninua.appfromscratch.app.TheApp
import meugeninua.appfromscratch.app.di.modules.AppModule
import meugeninua.appfromscratch.app.di.modules.ComponentsModule

@Component(modules = [AppModule::class,
    ComponentsModule::class])
@Singleton
interface AppComponent : AndroidInjector<TheApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<TheApp>()
}
