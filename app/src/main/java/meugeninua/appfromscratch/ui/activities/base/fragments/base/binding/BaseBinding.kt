package meugeninua.appfromscratch.ui.activities.base.fragments.base.binding

import android.support.v4.util.SparseArrayCompat
import android.view.View

import java.lang.ref.WeakReference

abstract class BaseBinding {

    private var rootViewRef: WeakReference<View>? = null
    private var childrenViewRefs: SparseArrayCompat<WeakReference<View>>? = null

    fun attachView(view: View) {
        rootViewRef = WeakReference(view)
        childrenViewRefs = SparseArrayCompat()
    }

    fun detachView() {
        rootViewRef = null
        childrenViewRefs?.clear()
        childrenViewRefs = null
    }

    private fun <V : View> getNullable(id: Int): V? {
        val rootViewRef = this.rootViewRef ?: return null
        val childrenViewRefs = this.childrenViewRefs ?: return null
        val rootView = rootViewRef.get() ?: return null
        val childViewRef = childrenViewRefs.get(id)
        var view: View? = childViewRef?.get()
        if (view == null) {
            view = rootView.findViewById(id)
            if (view != null) {
                childrenViewRefs.put(id, WeakReference(view))
            }
        }
        return view as V?
    }

    operator fun <V : View> get(id: Int): V {
        return getNullable(id) ?: throw IllegalArgumentException("View not found")
    }

    fun has(id: Int): Boolean {
        val view = getNullable<View>(id)
        return view != null
    }
}
