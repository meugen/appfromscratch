package meugeninua.appfromscratch.app.di.keys;

import android.arch.lifecycle.ViewModel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import dagger.MapKey;

@MapKey
@Retention(RetentionPolicy.SOURCE)
public @interface ViewModelKey {

    Class<? extends ViewModel> value();
}
