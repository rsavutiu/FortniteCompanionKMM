package org.smartmuseum.fortnitecompanion

import android.content.res.Configuration
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import org.smartmuseum.fortnitecompanion.ui.screens.MainView

class MainActivity : ComponentActivity() {
    private val _layoutConfigIsPortrait: MutableState<Boolean> = mutableStateOf(true)
    private val layoutConfigIsPortrait: State<Boolean> get() = _layoutConfigIsPortrait
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getLayoutState()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            MainView(kmmSharedPrefs = this.application, layoutConfigIsPortrait)
        }
    }

    override fun onResume() {
        super.onResume()
        getLayoutState()
    }

    private fun getLayoutState() {
        _layoutConfigIsPortrait.value =
            window.layoutInflater.context.resources.configuration.orientation.let {
                it == Configuration.ORIENTATION_PORTRAIT
            }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        _layoutConfigIsPortrait.value =
            (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
    }
}
