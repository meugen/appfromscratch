package meugeninua.appfromscratch.ui.activities.base.fragments.base.binding

import android.view.View

interface Binding {

    fun attachView(view: View)

    fun detachView()

    operator fun <V : View> get(id: Int): V

    fun has(id: Int): Boolean
}
