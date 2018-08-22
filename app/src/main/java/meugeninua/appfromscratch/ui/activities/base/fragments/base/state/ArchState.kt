package meugeninua.appfromscratch.ui.activities.base.fragments.base.state

import android.os.Bundle

interface ArchState {

    fun restore(bundle: Bundle)

    fun save(bundle: Bundle)
}
