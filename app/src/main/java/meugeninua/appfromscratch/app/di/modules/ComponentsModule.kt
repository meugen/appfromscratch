package meugeninua.appfromscratch.app.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import meugeninua.appfromscratch.ui.activities.main.MainActivity
import meugeninua.appfromscratch.ui.activities.main.MainActivityModule

@Module
interface ComponentsModule {

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    fun contributeMainActivity(): MainActivity
}
