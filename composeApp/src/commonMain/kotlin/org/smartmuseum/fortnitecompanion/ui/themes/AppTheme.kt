// Generated using MaterialKolor Builder version 1.0.1 (101)
// https://materialkolor.com/?color_seed=FF4C96C9&dark_mode=true&style=Vibrant

package org.smartmuseum.fortnitecompanion.ui.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import dev.icerock.moko.resources.compose.fontFamilyResource
import org.smartmuseum.fortnitecompanion.resources

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = primaryContainerLight,
    onSecondaryContainer = onPrimaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
//    TODO: When supported in Compose Multiplatform, this should be reenabled and used
//    outlineVariant = outlineVariantLight,
//    scrim = scrimLight,
//    surfaceDim = surfaceDimLight,
//    surfaceBright = surfaceBrightLight,
//    surfaceContainerLowest = surfaceContainerLowestLight,
//    surfaceContainerLow = surfaceContainerLowLight,
//    surfaceContainer = surfaceContainerLight,
//    surfaceContainerHigh = surfaceContainerHighLight,
//    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
//    TODO: When supported in Compose Multiplatform, this should be reenabled and used
//    outlineVariant = outlineVariantDark,
//    scrim = scrimDark,
//    surfaceDim = surfaceDimDark,
//    surfaceBright = surfaceBrightDark,
//    surfaceContainerLowest = surfaceContainerLowestDark,
//    surfaceContainerLow = surfaceContainerLowDark,
//    surfaceContainer = surfaceContainerDark,
//    surfaceContainerHigh = surfaceContainerHighDark,
//    surfaceContainerHighest = surfaceContainerHighestDark,
)

val fortniteFontFamily: FontFamily
    @Composable
    get() = fontFamilyResource(resources.fonts.fortnite)


val FortniteTypography
    @Composable // Composable is required for loading font from compose resources
    get() = Typography(
        displayLarge = TextStyle(
            fontFamily = fortniteFontFamily,
            fontSize = MaterialTheme.typography.displayLarge.fontSize
        ),
        displayMedium = TextStyle(
            fontFamily = fortniteFontFamily,
            fontSize = MaterialTheme.typography.displayMedium.fontSize
        ),
        displaySmall = TextStyle(
            fontFamily = fortniteFontFamily,
            fontSize = MaterialTheme.typography.displaySmall.fontSize
        ),
        headlineLarge = TextStyle(
            fontFamily = fortniteFontFamily,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize
        ),
        headlineMedium = TextStyle(
            fontFamily = fortniteFontFamily,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize
        ),
        headlineSmall = TextStyle(
            fontFamily = fortniteFontFamily,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize
        ),
        titleLarge = TextStyle(
            fontFamily = fortniteFontFamily,
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        ),
        titleMedium = TextStyle(
            fontFamily = fortniteFontFamily,
            fontSize = MaterialTheme.typography.titleMedium.fontSize
        ),
        titleSmall = TextStyle(
            fontFamily = fortniteFontFamily,
            fontSize = MaterialTheme.typography.titleSmall.fontSize
        ),
        bodyLarge = TextStyle(
            fontFamily = fortniteFontFamily,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
        ),
        bodyMedium = TextStyle(
            fontFamily = fortniteFontFamily,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize
        ),
        bodySmall = TextStyle(
            fontFamily = fortniteFontFamily,
            fontSize = MaterialTheme.typography.bodySmall.fontSize
        ),
        labelLarge = TextStyle(
            fontFamily = fortniteFontFamily,
            fontSize = MaterialTheme.typography.labelLarge.fontSize
        ),
        labelMedium = TextStyle(
            fontFamily = fortniteFontFamily,
            fontSize = MaterialTheme.typography.labelMedium.fontSize
        ),
        labelSmall = TextStyle(
            fontFamily = fortniteFontFamily,
            fontSize = MaterialTheme.typography.labelSmall.fontSize
        )
    )


@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
    val colorScheme = when {
//      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//          val context = LocalContext.current
//          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//      }

        darkTheme -> darkScheme
        else -> lightScheme
    }

    androidx.compose.material3.MaterialTheme(
        colorScheme = colorScheme,
        typography = FortniteTypography,
        content = content
    )
}
