package org.smartmuseum.fortnitecompanion.ui.screens.cosmetics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.smartmuseum.fortnitecompanion.data.cosmetics.ICosmetic
import org.smartmuseum.fortnitecompanion.ui.themes.AppTheme
import org.smartmuseum.fortnitecompanion.ui.themes.fortniteFontFamily
import org.smartmuseum.fortnitecompanion.utils.getRelativeTimeText

@Composable
fun FullCosmeticScreen(fullCosmeticState: State<ICosmetic?>) {
    val imageModifier = Modifier
        .size(256.dp)
        .defaultMinSize(minHeight = 128.dp, minWidth = 128.dp)
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            val fullCosmetic = fullCosmeticState.value
            if (fullCosmetic != null) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val imgUrl: String? = fullCosmetic.getIcon() ?: fullCosmetic.getSmallIcon()
                    imgUrl?.let { icon ->
                        AsyncImage(
                            modifier = imageModifier.align(Alignment.CenterHorizontally),
                            model = icon,
                            contentDescription = null
                        )
                    }
                    Text(
                        text = fullCosmetic.getNameOrId(),
                        style = MaterialTheme.typography.displayLarge
                    )
                    fullCosmetic.getCosmeticDescription()?.let {
                        Text(
                            modifier = Modifier.padding(bottom = 4.dp),
                            text = it,
                            style = MaterialTheme.typography.headlineLarge,
                            fontFamily = fortniteFontFamily
                        )
                    }
                    fullCosmetic.getCosmeticType()?.let {
                        Text(
                            modifier = Modifier.padding(bottom = 4.dp),
                            text = "Type: ${it.displayValue}",
                            style = MaterialTheme.typography.headlineMedium,
                        )
                    }
                    fullCosmetic.getCosmeticRarity()?.let {
                        Text(
                            modifier = Modifier.padding(bottom = 4.dp),
                            text = "Rarity: ${it.displayValue}",
                            style = MaterialTheme.typography.bodyLarge, // M3 body
                        )
                    }
                    fullCosmetic.getCosmeticIntroduction()?.let {
                        Text(
                            modifier = Modifier.padding(bottom = 4.dp),
                            text = it.text,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                    fullCosmetic.getDate()?.let {
                        Text(
                            modifier = Modifier.padding(bottom = 4.dp),
                            text = it.getRelativeTimeText(),
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                }
            }
        }
    }
}