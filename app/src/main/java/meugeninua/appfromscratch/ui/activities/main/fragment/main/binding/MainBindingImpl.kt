package meugeninua.appfromscratch.ui.activities.main.fragment.main.binding

import android.view.View
import android.widget.TextView

import java.lang.ref.WeakReference
import java.text.DateFormat
import java.util.Date

import javax.inject.Inject

import meugeninua.appfromscratch.R
import meugeninua.appfromscratch.ui.activities.base.fragments.base.binding.BaseBinding
import meugeninua.appfromscratch.ui.activities.main.fragment.main.view.MainView

class MainBindingImpl @Inject
internal constructor() : BaseBinding(), MainBinding {

    private val formatter: DateFormat

    init {
        this.formatter = DateFormat.getDateTimeInstance()
    }

    override fun displayTime() {
        val messageView = get<TextView>(R.id.message)
        messageView.text = formatter.format(Date())
    }

    override fun setupListeners(view: MainView) {
        val clickListener = OnClickListenerImpl(view)
        get<View>(R.id.start).setOnClickListener(clickListener)
        get<View>(R.id.stop).setOnClickListener(clickListener)
    }

    override fun enableButtons(started: Boolean) {
        get<View>(R.id.start).isEnabled = !started
        get<View>(R.id.stop).isEnabled = started
    }

    private class OnClickListenerImpl internal constructor(mainView: MainView) : View.OnClickListener {

        private val ref: WeakReference<MainView>

        init {
            this.ref = WeakReference(mainView)
        }

        override fun onClick(view: View) {
            val mainView = ref.get() ?: return

            val viewId = view.id
            if (viewId == R.id.start) {
                mainView.onStartClicked()
            } else if (viewId == R.id.stop) {
                mainView.onStopClicked()
            }
        }
    }
}
