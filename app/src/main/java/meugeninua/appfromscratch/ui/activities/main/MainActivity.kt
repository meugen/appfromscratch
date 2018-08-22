package meugeninua.appfromscratch.ui.activities.main

import android.os.Bundle

import meugeninua.appfromscratch.R
import meugeninua.appfromscratch.ui.activities.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
