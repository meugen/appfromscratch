package meugeninua.appfromscratch.ui.activities.main.fragment.main.binding;

import meugeninua.appfromscratch.ui.activities.base.fragments.base.binding.Binding;
import meugeninua.appfromscratch.ui.activities.main.fragment.main.view.MainView;

public interface MainBinding extends Binding {

    void displayTime();

    void setupListeners(MainView view);

    void enableButtons(boolean started);
}
