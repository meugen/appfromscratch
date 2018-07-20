package meugeninua.appfromscratch.ui.activities.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import dagger.Binds;
import dagger.Module;
import meugeninua.appfromscratch.app.di.qualifiers.ActivityContext;

@Module
public interface BaseActivityModule {

    @Binds @ActivityContext
    Context bindActivityContext(AppCompatActivity activity);
}
