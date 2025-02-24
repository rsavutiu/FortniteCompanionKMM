package org.smartmuseum.fortnitecompanion.ui.molecules

import VideoPlayer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import org.smartmuseum.fortnitecompanion.data.cosmetics.ICosmetic
import org.smartmuseum.fortnitecompanion.ui.nav.NavigationGraphs
import org.smartmuseum.fortnitecompanion.ui.nav.NavigationItem
import org.smartmuseum.fortnitecompanion.ui.themes.fortniteFontFamily
import org.smartmuseum.fortnitecompanion.utils.getRelativeTimeText

@Composable
fun CosmeticItem(
    navController: NavController,
    fortniteCosmetic: ICosmetic,
    useSmallIcon: Boolean = false,
    onSelectCosmetic: (ICosmetic) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                fortniteCosmetic.getIcon()?.let {
                    onSelectCosmetic(fortniteCosmetic)
                    navController.navigate(route = NavigationGraphs.CosmeticsGraph.route)
                    navController.navigate(route = NavigationItem.FullCosmetic.route)
                }
            },
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.1f),
            disabledContentColor = MaterialTheme.colorScheme.inverseOnSurface
        )
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Image
                val imageModifier = Modifier
                    .size(if (useSmallIcon) 64.dp else 128.dp) // M3 standard sizes
                    .defaultMinSize(minHeight = 50.dp)

                if (useSmallIcon) {
                    fortniteCosmetic.getSmallIcon()?.let { icon ->
                        AsyncImage(
                            modifier = imageModifier,
                            model = icon,
                            contentDescription = null
                        )
                    }
                } else {
                    fortniteCosmetic.getIcon()?.let { icon ->
                        AsyncImage(
                            modifier = imageModifier,
                            model = icon,
                            contentDescription = null
                        )
                    }
                }

                Spacer(modifier = Modifier.width(8.dp)) // M3 min spacing
                // Text Information
                Column {
                    Text(
                        modifier = Modifier.padding(bottom = 4.dp),
                        text = fortniteCosmetic.getNameOrId(),
                        style = MaterialTheme.typography.titleMedium
                    )
                    fortniteCosmetic.getCosmeticDescription()?.let {
                        Text(
                            modifier = Modifier.padding(bottom = 4.dp),
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            fontFamily = fortniteFontFamily
                        )
                    }
                    fortniteCosmetic.getCosmeticType()?.let {
                        Text(
                            modifier = Modifier.padding(bottom = 4.dp),
                            text = "Type: ${it.displayValue}",
                            style = MaterialTheme.typography.labelLarge,
                        )
                    }
                    fortniteCosmetic.getCosmeticRarity()?.let {
                        Text(
                            modifier = Modifier.padding(bottom = 4.dp),
                            text = "Rarity: ${it.displayValue}",
                            style = MaterialTheme.typography.labelMedium,
                        )
                    }
                    fortniteCosmetic.getCosmeticIntroduction()?.let {
                        Text(
                            modifier = Modifier.padding(bottom = 4.dp),
                            text = it.text,
                            style = MaterialTheme.typography.labelMedium, // M3 body
                        )
                    }
                    fortniteCosmetic.getDate()?.let {
                        Text(
                            modifier = Modifier.padding(bottom = 4.dp),
                            text = it.getRelativeTimeText(),
                            style = MaterialTheme.typography.labelSmall, // M3 body
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            // Video (if available)
            fortniteCosmetic.getVideoUrl()?.let { url ->
                Spacer(modifier = Modifier.height(16.dp)) // M3 standard spacing
                VideoPlayer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(340.dp),
                    url = url,
                    showControls = true,
                    autoPlay = false
                )
            }
        }
    }
}
