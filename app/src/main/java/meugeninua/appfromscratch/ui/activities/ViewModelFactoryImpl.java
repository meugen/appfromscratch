package meugeninua.appfromscratch.ui.activities;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

public class ViewModelFactoryImpl implements ViewModelProvider.Factory {

    @Inject Map<Class<? extends ViewModel>, Provider<ViewModel>> providers;

    @Inject
    ViewModelFactoryImpl() {}

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        final Provider<ViewModel> provider = providers.get(modelClass);
        if (provider == null) {
            throw new IllegalArgumentException("Provider for " + modelClass + " is not found");
        }
        return (T) provider.get();
    }
}
