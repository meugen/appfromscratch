package meugeninua.appfromscratch.ui.activities.base.fragments.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import meugeninua.appfromscratch.ui.activities.base.fragments.base.binding.Binding;
import meugeninua.appfromscratch.ui.activities.base.fragments.base.state.ArchState;

public abstract class BaseFragment<B extends Binding, S extends ArchState> extends Fragment {

    @Inject protected B binding;
    @Inject protected S state;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        state.restore(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
        state.save(outState);
    }

    @Override
    public void onViewCreated(
            @NonNull final View view,
            @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.attachView(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.detachView();
    }
}
