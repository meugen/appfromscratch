package meugeninua.appfromscratch.ui.activities.base.fragments.base.state

import android.os.Bundle

import javax.inject.Inject

class ArchStateImpl @Inject
internal constructor() : ArchState {

    override fun restore(bundle: Bundle) {}

    override fun save(bundle: Bundle) {}
}
