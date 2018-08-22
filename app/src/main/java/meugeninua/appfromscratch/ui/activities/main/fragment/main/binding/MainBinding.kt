package meugeninua.appfromscratch.ui.activities.main.fragment.main.binding

import meugeninua.appfromscratch.ui.activities.base.fragments.base.binding.Binding
import meugeninua.appfromscratch.ui.activities.main.fragment.main.view.MainView

interface MainBinding : Binding {

    fun displayTime()

    fun setupListeners(view: MainView)

    fun enableButtons(started: Boolean)
}
