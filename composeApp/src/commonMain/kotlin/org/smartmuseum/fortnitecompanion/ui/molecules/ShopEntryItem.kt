package org.smartmuseum.fortnitecompanion.ui.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fortnitecompanionapp.composeapp.generated.resources.Res
import fortnitecompanionapp.composeapp.generated.resources.final_price
import fortnitecompanionapp.composeapp.generated.resources.regular_price
import org.jetbrains.compose.resources.stringResource
import org.smartmuseum.fortnitecompanion.data.shop.ShopEntry
import org.smartmuseum.fortnitecompanion.ui.components.ImageSlider
import org.smartmuseum.fortnitecompanion.utils.formatDate

@Composable
fun ShopEntryItem(entry: ShopEntry) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.inverseSurface,
            disabledContentColor = MaterialTheme.colorScheme.inverseOnSurface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = entry.devName,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            if (entry.regularPrice == entry.finalPrice) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.regular_price, "${entry.finalPrice}"),
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(
                            Res.string.regular_price,
                            "${entry.regularPrice}"
                        ),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = stringResource(Res.string.final_price, "${entry.finalPrice}"),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            entry.offerTag?.let { offerTag ->
                Text(
                    text = offerTag.text,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
//            Spacer(modifier = Modifier.height(4.dp))
//            entry.layout?.let { entryLayout ->
//                LayoutItem(layout = entryLayout)
//            }
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = "Tile Size: ${entry.tileSize}",
//                style = MaterialTheme.typography.bodyMedium
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = "New Display Asset Path: ${entry.newDisplayAssetPath}",
//                style = MaterialTheme.typography.bodyMedium
//            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Giftable: ${entry.giftable}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Refundable: ${entry.refundable}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = "Sort Priority: ${entry.sortPriority}",
//                style = MaterialTheme.typography.bodyMedium
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = "Offer Id: ${entry.offerId}",
//                style = MaterialTheme.typography.bodyMedium
//            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = entry.inDate.formatDate(pattern = "dd MMM ''yy HH:mm"),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text("<----->")
                Text(
                    text = entry.outDate.formatDate(pattern = "dd MMM ''yy HH:mm"),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(8.dp))
            entry.tracks?.forEach { track ->
                Text(
                    text = "Tracks:",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                TrackItem(track = track)
            }
            ImageSlider(
                modifier = Modifier.height(350.dp).fillMaxWidth(),
                listOfUrls = entry.newDisplayAsset?.renderImages?.map { it.image }?.filterNotNull()
                    ?: emptyList()
            )
        }
    }
}