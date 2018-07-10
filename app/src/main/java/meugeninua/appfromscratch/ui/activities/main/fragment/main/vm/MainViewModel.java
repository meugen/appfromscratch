package meugeninua.appfromscratch.ui.activities.main.fragment.main.vm;

import android.arch.lifecycle.ViewModel;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import meugeninua.appfromscratch.app.managers.AppEventsManager;
import meugeninua.appfromscratch.app.managers.events.DisplayTimeEvent;
import timber.log.Timber;

public class MainViewModel extends ViewModel {

    @Inject AppEventsManager eventsManager;

    private ScheduledExecutorService executor;

    @Inject
    MainViewModel() {}

    public void start() {
        if (executor != null) {
            return;
        }
        executor = Executors.newScheduledThreadPool(2);
        executor.scheduleWithFixedDelay(
                new RunnableImpl(this),
                0, 1, TimeUnit.SECONDS);
    }

    public void stop() {
        if (executor == null) {
            return;
        }
        executor.shutdown();
        executor = null;
    }

    public boolean isStarted() {
        return executor != null;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Timber.d("onCleared() method called");
        stop();
    }

    private void displayTime() {
        eventsManager.post(new DisplayTimeEvent());
    }

    private static class RunnableImpl implements Runnable {

        private final WeakReference<MainViewModel> ref;

        RunnableImpl(final MainViewModel viewModel) {
            this.ref = new WeakReference<>(viewModel);
        }

        @Override
        public void run() {
            final MainViewModel viewModel = ref.get();
            if (viewModel != null) {
                viewModel.displayTime();
            }
        }
    }
}
