package meugeninua.appfromscratch.ui.activities.base

import android.content.Context
import android.support.v7.app.AppCompatActivity

import dagger.Binds
import dagger.Module
import meugeninua.appfromscratch.app.di.qualifiers.ActivityContext

@Module
interface BaseActivityModule {

    @Binds
    @ActivityContext
    fun bindActivityContext(activity: AppCompatActivity): Context
}
