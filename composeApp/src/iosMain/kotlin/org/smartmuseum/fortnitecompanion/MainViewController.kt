package org.smartmuseum.fortnitecompanion

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.window.ComposeUIViewController
import org.smartmuseum.fortnitecompanion.di.KoinInitializer
import org.smartmuseum.fortnitecompanion.ui.screens.MainView
import platform.darwin.NSObject

fun MainViewController() = ComposeUIViewController(
    configure = {
        KoinInitializer().init()
    }
) {
    MainView(NSObject(), layoutConfigIsPortrait = mutableStateOf(true))
}