package meugeninua.appfromscratch.app.managers

import android.arch.lifecycle.GenericLifecycleObserver
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.os.Handler
import android.os.Looper
import android.support.annotation.AnyThread
import android.support.annotation.MainThread
import android.support.v4.util.ArrayMap

import java.lang.ref.WeakReference
import java.util.ArrayList
import java.util.UUID

import javax.inject.Inject
import javax.inject.Singleton

import timber.log.Timber

@Singleton
class AppEventsManager @Inject internal constructor() {

    private val observers: MutableMap<UUID, ObserverWrapper<*>> = ArrayMap()
    internal val handler: Handler = Handler(Looper.getMainLooper())

    @AnyThread
    fun post(event: Any) {
        Timber.d(Thread.currentThread().name)

        lateinit var wrappers: List<ObserverWrapper<*>>
        synchronized(this) {
            wrappers = ArrayList(observers.values)
        }
        for (wrapper in wrappers) {
            wrapper.update(this, event)
        }
    }

    @MainThread
    fun <T> subscribeToEvent(
            owner: LifecycleOwner,
            clazz: Class<T>,
            observer: (T) -> Unit) {
        val impl = ObserverWrapper<T>()
        impl.observer = observer
        impl.clazz = clazz
        impl.ownerRef = WeakReference(owner)
        impl.managerRef = WeakReference(this)

        var key: UUID = UUID.randomUUID()
        synchronized(this) {
            while (observers.containsKey(key)) {
                key = UUID.randomUUID()
            }
            impl.key = key
            observers.put(key, impl)
        }
        impl.attachToLifecycle()
    }

    @MainThread
    @Synchronized
    internal fun unsubscribe(key: UUID?) {
        Timber.d("Unsubscribed %s", key)
        val wrapper = observers.remove(key)
        wrapper?.detachFromLifecycle()
    }

    private class ObserverWrapper<T> : GenericLifecycleObserver {

        lateinit var clazz: Class<T>
        lateinit var observer: (T) -> Unit
        lateinit var key: UUID
        lateinit var ownerRef: WeakReference<LifecycleOwner>
        lateinit var managerRef: WeakReference<AppEventsManager>

        override fun onStateChanged(
                source: LifecycleOwner,
                event: Lifecycle.Event) {
            val manager = managerRef.get()
            if (manager != null && source.lifecycle
                            .currentState == Lifecycle.State.DESTROYED) {
                manager.unsubscribe(key)
            }
        }

        internal fun attachToLifecycle() {
            val owner = ownerRef.get() ?: return
            owner.lifecycle.addObserver(this)
        }

        @MainThread
        internal fun detachFromLifecycle() {
            val owner = ownerRef.get() ?: return
            owner.lifecycle.removeObserver(this)
        }

        @AnyThread
        internal fun update(
                manager: AppEventsManager,
                arg: Any) {
            if (clazz.isInstance(arg)) {
                manager.handler.post { observer(clazz.cast(arg)) }
            }
        }
    }
}
