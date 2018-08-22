package meugeninua.appfromscratch.ui.activities.main.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import javax.inject.Inject

import meugeninua.appfromscratch.R
import meugeninua.appfromscratch.app.managers.AppEventsManager
import meugeninua.appfromscratch.app.managers.events.DisplayTimeEvent
import meugeninua.appfromscratch.ui.activities.base.fragments.base.BaseFragment
import meugeninua.appfromscratch.ui.activities.base.fragments.base.state.ArchState
import meugeninua.appfromscratch.ui.activities.main.fragment.main.binding.MainBinding
import meugeninua.appfromscratch.ui.activities.main.fragment.main.view.MainView
import meugeninua.appfromscratch.ui.activities.main.fragment.main.vm.MainViewModel
import timber.log.Timber

class MainFragment : BaseFragment<MainBinding, ArchState>(), MainView {

    @Inject
    lateinit var eventsManager: AppEventsManager
    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Timber.d("%s", viewModel)
        eventsManager.subscribeToEvent(
                this,
                DisplayTimeEvent::class.java) { onDisplayTime(it) }
        binding.setupListeners(this)
        binding.enableButtons(viewModel.isStarted)
    }

    private fun onDisplayTime(event: DisplayTimeEvent) {
        Timber.d("%s", event)
        binding.displayTime()
    }

    override fun onStartClicked() {
        viewModel.start()
        binding.enableButtons(true)
    }

    override fun onStopClicked() {
        viewModel.stop()
        binding.enableButtons(false)
    }
}
