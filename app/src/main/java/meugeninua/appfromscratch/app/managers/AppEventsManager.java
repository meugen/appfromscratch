package meugeninua.appfromscratch.app.managers;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.AnyThread;
import android.support.annotation.MainThread;
import android.support.v4.util.ArrayMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import meugeninua.appfromscratch.app.di.scopes.PerApplication;
import timber.log.Timber;

@PerApplication
public class AppEventsManager {

    private final Map<UUID, ObserverWrapper<?>> observers;
    final Handler handler;

    @Inject
    AppEventsManager() {
        observers = new ArrayMap<>();
        handler = new Handler(Looper.getMainLooper());
    }

    @AnyThread
    public void post(final Object event) {
        Timber.d(Thread.currentThread().getName());

        final List<ObserverWrapper<?>> wrappers;
        synchronized (this) {
            wrappers = new ArrayList<>(observers.values());
        }
        for (ObserverWrapper<?> wrapper : wrappers) {
            wrapper.update(this, event);
        }
    }

    @AnyThread
    public <T> UUID subscribeToEvent(
            final Class<T> clazz,
            final TypedObserver<T> observer) {
        UUID key = null;
        synchronized (this) {
            while (key == null || observers.containsKey(key)) {
                key = UUID.randomUUID();
            }
            final ObserverWrapper<T> impl = new ObserverWrapper<>(
                    clazz, observer);
            observers.put(key, impl);
        }
        return key;
    }

    @AnyThread
    public void unsubscribe(final UUID... keys) {
        unsubscribe(Arrays.asList(keys));
    }

    @AnyThread
    public synchronized void unsubscribe(final Collection<UUID> keys) {
        for (UUID key : keys) {
            Timber.d("Unsubscribed %s", key);
            observers.remove(key);
        }
    }

    private static class ObserverWrapper<T> {

        private final Class<T> clazz;
        private final TypedObserver<T> observer;

        ObserverWrapper(
                final Class<T> clazz,
                final TypedObserver<T> observer) {
            this.clazz = clazz;
            this.observer = observer;
        }

        @AnyThread
        void update(
                final AppEventsManager manager,
                final Object arg) {
            if (clazz.isInstance(arg)) {
                manager.handler.post(() -> observer.onUpdate(clazz.cast(arg)));
            }
        }
    }

    public interface TypedObserver<T> {

        @MainThread
        void onUpdate(T event);
    }
}
