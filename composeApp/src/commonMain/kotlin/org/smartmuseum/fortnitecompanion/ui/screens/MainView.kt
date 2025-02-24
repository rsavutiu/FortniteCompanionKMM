package org.smartmuseum.fortnitecompanion.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.compose.KoinContext
import org.koin.compose.currentKoinScope
import org.smartmuseum.fortnitecompanion.storage.KMMSharedPrefs
import org.smartmuseum.fortnitecompanion.ui.nav.AppNavHost
import org.smartmuseum.fortnitecompanion.ui.themes.AppTheme

@Composable
fun MainView(
    kmmSharedPrefs: KMMSharedPrefs,
    layoutConfigIsPortrait: State<Boolean>
) {
    AppTheme {
        KoinContext {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                AppNavHost(
                    kmmSharedPrefs = kmmSharedPrefs,
                    modifier = Modifier,
                    layoutConfigIsPortrait = layoutConfigIsPortrait
                )
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> koinViewModel(): T {
    val scope = currentKoinScope()
    return viewModel {
        scope.get<T>()
    }
}