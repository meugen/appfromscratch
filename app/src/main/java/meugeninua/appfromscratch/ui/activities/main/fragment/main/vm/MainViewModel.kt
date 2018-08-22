package meugeninua.appfromscratch.ui.activities.main.fragment.main.vm

import android.arch.lifecycle.ViewModel

import java.lang.ref.WeakReference
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

import javax.inject.Inject

import meugeninua.appfromscratch.app.managers.AppEventsManager
import meugeninua.appfromscratch.app.managers.events.DisplayTimeEvent
import timber.log.Timber

class MainViewModel @Inject internal constructor() : ViewModel() {

    @Inject
    lateinit var eventsManager: AppEventsManager

    private var executor: ScheduledExecutorService? = null

    val isStarted: Boolean
        get() = executor != null

    fun start() {
        if (executor != null) {
            return
        }
        executor = Executors.newScheduledThreadPool(2)
        executor!!.scheduleWithFixedDelay(
                RunnableImpl(this),
                0, 1, TimeUnit.SECONDS)
    }

    fun stop() {
        val executor = this.executor ?: return
        executor.shutdown()
        this.executor = null
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("onCleared() method called")
        stop()
    }

    private fun displayTime() {
        eventsManager.post(DisplayTimeEvent())
    }

    private class RunnableImpl internal constructor(viewModel: MainViewModel) : Runnable {

        private val ref: WeakReference<MainViewModel> = WeakReference(viewModel)

        override fun run() {
            val viewModel = ref.get()
            viewModel?.displayTime()
        }
    }
}
