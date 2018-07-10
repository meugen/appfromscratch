package meugeninua.appfromscratch.ui.activities.main.fragment.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import meugeninua.appfromscratch.R;
import meugeninua.appfromscratch.app.managers.AppEventsManager;
import meugeninua.appfromscratch.app.managers.events.DisplayTimeEvent;
import meugeninua.appfromscratch.ui.activities.base.fragments.base.BaseFragment;
import meugeninua.appfromscratch.ui.activities.base.fragments.base.state.ArchState;
import meugeninua.appfromscratch.ui.activities.main.fragment.main.binding.MainBinding;
import meugeninua.appfromscratch.ui.activities.main.fragment.main.view.MainView;
import meugeninua.appfromscratch.ui.activities.main.fragment.main.vm.MainViewModel;
import timber.log.Timber;

public class MainFragment extends BaseFragment<MainBinding, ArchState> implements MainView {

    @Inject AppEventsManager eventsManager;
    @Inject MainViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container);
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Timber.d("%s", viewModel);
        eventsManager.subscribeToEvent(
                this,
                DisplayTimeEvent.class,
                this::onDisplayTime);
        binding.setupListeners(this);
        binding.enableButtons(viewModel.isStarted());
    }

    private void onDisplayTime(final DisplayTimeEvent event) {
        Timber.d("%s", event);
        binding.displayTime();
    }

    @Override
    public void onStartClicked() {
        viewModel.start();
        binding.enableButtons(true);
    }

    @Override
    public void onStopClicked() {
        viewModel.stop();
        binding.enableButtons(false);
    }
}
