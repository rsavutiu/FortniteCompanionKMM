package org.smartmuseum.fortnitecompanion.ui.screens.generic

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import fortnitecompanionapp.composeapp.generated.resources.Res
import fortnitecompanionapp.composeapp.generated.resources.baseline_downloading_24
import fortnitecompanionapp.composeapp.generated.resources.loading
import fortnitecompanionapp.composeapp.generated.resources.loading1
import fortnitecompanionapp.composeapp.generated.resources.loading2
import fortnitecompanionapp.composeapp.generated.resources.loading3
import fortnitecompanionapp.composeapp.generated.resources.loading4
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.smartmuseum.fortnitecompanion.ui.molecules.ImageCarousel

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        ImageCarousel(
            modifier = modifier, images = listOf(
                Res.drawable.loading1,
                Res.drawable.loading2,
                Res.drawable.loading3,
                Res.drawable.loading4
            )
        )

        // Animated Loading Text
        val textAlpha = remember { Animatable(0f) }
        LaunchedEffect(key1 = true) {
            textAlpha.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1000, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                )
            )
        }
        Text(
            text = stringResource(Res.string.loading),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier
                .align(Alignment.Center)
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(16.dp)
                .alpha(textAlpha.value) // Apply the alpha animation
        )

        // Animated Loading Icon
        val iconRotation = remember { Animatable(0f) }
        LaunchedEffect(key1 = true) {
            iconRotation.animateTo(
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(2000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            )
        }
        Image(
            painter = painterResource(Res.drawable.baseline_downloading_24),
            contentDescription = "Loading",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .size(64.dp)
                .rotate(iconRotation.value) // Apply the rotation animation
        )
    }
}