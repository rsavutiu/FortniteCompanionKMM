package org.smartmuseum.fortnitecompanion.ui.previews

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.smartmuseum.fortnitecompanion.ui.screens.generic.LoadingScreen
import org.smartmuseum.fortnitecompanion.ui.themes.AppTheme


@Composable
@Preview
fun LoadingScreenPreview() {
    AppTheme {
        LoadingScreen(modifier = Modifier.fillMaxSize())
    }
}