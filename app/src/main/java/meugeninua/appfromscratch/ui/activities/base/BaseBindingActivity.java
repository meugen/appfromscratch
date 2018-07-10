package meugeninua.appfromscratch.ui.activities.base;

import javax.inject.Inject;

import meugeninua.appfromscratch.ui.activities.base.fragments.base.binding.Binding;

public abstract class BaseBindingActivity<B extends Binding> extends BaseActivity {

    @Inject protected B binding;

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        binding.attachView(getWindow().getDecorView());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.detachView();
    }
}
