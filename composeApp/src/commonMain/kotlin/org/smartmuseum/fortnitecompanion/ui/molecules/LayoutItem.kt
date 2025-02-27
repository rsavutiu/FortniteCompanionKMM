package org.smartmuseum.fortnitecompanion.ui.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import org.smartmuseum.fortnitecompanion.data.shop.Layout

@Composable
fun LayoutItem(layout: Layout) {
    Column {
        Text(
            text = "Layout:",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
        layout.id?.let { layoutId ->
            Text(
                text = "Id: $layoutId",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        layout.name?.let { layoutName ->
            Text(
                text = "Name: $layoutName",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        layout.category?.let { layoutCategory ->
            Text(
                text = "Category: $layoutCategory",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        layout.index?.let { layoutIndex ->
            Text(
                text = "Index: $layoutIndex",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        layout.rank?.let { layoutRank ->
            Text(
                text = "Rank: $layoutRank",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        layout.showIneligibleOffers?.let { showIneligibleOffers ->
            Text(
                text = "Show Ineligible Offers: ${showIneligibleOffers}",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        layout.useWidePreview?.let {
            Text(
                text = "Use Wide Preview: ${layout.useWidePreview}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        layout.displayType?.let {
            Text(
                text = "Display Type: ${layout.displayType}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}