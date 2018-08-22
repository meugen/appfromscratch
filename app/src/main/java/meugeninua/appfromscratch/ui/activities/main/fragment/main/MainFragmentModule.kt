package meugeninua.appfromscratch.ui.activities.main.fragment.main

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment

import dagger.Binds
import dagger.Module
import dagger.Provides
import meugeninua.appfromscratch.ui.activities.base.fragments.base.BaseFragmentModule
import meugeninua.appfromscratch.ui.activities.base.fragments.base.state.ArchState
import meugeninua.appfromscratch.ui.activities.base.fragments.base.state.ArchStateImpl
import meugeninua.appfromscratch.ui.activities.main.fragment.main.binding.MainBinding
import meugeninua.appfromscratch.ui.activities.main.fragment.main.binding.MainBindingImpl
import meugeninua.appfromscratch.ui.activities.main.fragment.main.vm.MainViewModel

@Module(includes = arrayOf(BaseFragmentModule::class))
abstract class MainFragmentModule {

    @Binds
    abstract fun bindFragment(fragment: MainFragment): Fragment

    @Binds
    abstract fun bindBinding(impl: MainBindingImpl): MainBinding

    @Binds
    abstract fun bindState(impl: ArchStateImpl): ArchState

    @Module
    companion object {

        @JvmStatic @Provides
        fun provideMainViewModel(
                fragment: Fragment,
                factory: ViewModelProvider.Factory): MainViewModel {
            return ViewModelProviders
                    .of(fragment, factory)
                    .get(MainViewModel::class.java)
        }
    }
}
