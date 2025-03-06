package org.smartmuseum.fortnitecompanion.ui.screens.generic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fortnitecompanionapp.composeapp.generated.resources.Res
import fortnitecompanionapp.composeapp.generated.resources.no_internet_connection
import fortnitecompanionapp.composeapp.generated.resources.retry
import org.jetbrains.compose.resources.stringResource

@Composable
fun NoInternetScreen(onRetry: (() -> Unit)? = null) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(Res.string.no_internet_connection),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (onRetry != null) {
            Button(onClick = { onRetry() }) {
                Text(text = stringResource(Res.string.retry))
            }
        }
    }
}