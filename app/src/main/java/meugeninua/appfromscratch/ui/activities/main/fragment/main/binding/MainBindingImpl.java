package meugeninua.appfromscratch.ui.activities.main.fragment.main.binding;

import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.util.Date;

import javax.inject.Inject;

import meugeninua.appfromscratch.R;
import meugeninua.appfromscratch.ui.activities.base.fragments.base.binding.BaseBinding;
import meugeninua.appfromscratch.ui.activities.main.fragment.main.view.MainView;

public class MainBindingImpl extends BaseBinding
        implements MainBinding {

    private final DateFormat formatter;

    @Inject
    MainBindingImpl() {
        this.formatter = DateFormat.getDateTimeInstance();
    }

    @Override
    public void displayTime() {
        final TextView messageView = get(R.id.message);
        messageView.setText(formatter.format(new Date()));
    }

    @Override
    public void setupListeners(final MainView view) {
        final OnClickListenerImpl clickListener = new OnClickListenerImpl(view);
        get(R.id.start).setOnClickListener(clickListener);
        get(R.id.stop).setOnClickListener(clickListener);
    }

    @Override
    public void enableButtons(final boolean started) {
        get(R.id.start).setEnabled(!started);
        get(R.id.stop).setEnabled(started);
    }

    private static class OnClickListenerImpl implements View.OnClickListener {

        private final WeakReference<MainView> ref;

        OnClickListenerImpl(final MainView mainView) {
            this.ref = new WeakReference<>(mainView);
        }

        @Override
        public void onClick(final View view) {
            final MainView mainView = ref.get();
            if (mainView == null) {
                return;
            }

            final int viewId = view.getId();
            if (viewId == R.id.start) {
                mainView.onStartClicked();
            } else if (viewId == R.id.stop) {
                mainView.onStopClicked();
            }
        }
    }
}
