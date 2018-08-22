package meugeninua.appfromscratch.ui.activities.base.fragments.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

import javax.inject.Inject

import dagger.android.support.AndroidSupportInjection
import meugeninua.appfromscratch.ui.activities.base.fragments.base.binding.Binding
import meugeninua.appfromscratch.ui.activities.base.fragments.base.state.ArchState

abstract class BaseFragment<B : Binding, S : ArchState> : Fragment() {

    @Inject
    lateinit var binding: B
    @Inject
    lateinit var state: S

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        state.restore(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        state.save(outState)
    }

    override fun onViewCreated(
            view: View,
            savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.attachView(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.detachView()
    }
}
