package meugeninua.appfromscratch.ui.activities.base

import javax.inject.Inject

import meugeninua.appfromscratch.ui.activities.base.fragments.base.binding.Binding

abstract class BaseBindingActivity<B : Binding> : BaseActivity() {

    @Inject
    lateinit var binding: B

    override fun onContentChanged() {
        super.onContentChanged()
        binding.attachView(window.decorView)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.detachView()
    }
}
