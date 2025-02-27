package org.smartmuseum.fortnitecompanion.ui.molecules

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import org.smartmuseum.fortnitecompanion.data.shop.ShopData
import org.smartmuseum.fortnitecompanion.utils.formatDate

@Composable
fun ShopHeader(shopData: ShopData) {
    Row(
        modifier = Modifier.fillMaxWidth().background(color = MaterialTheme.colorScheme.surface)
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = shopData.vbuckIcon),
            contentDescription = "V-Bucks Icon",
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Shop - ${shopData.date.formatDate(pattern = "dd MMM ''yy HH:mm")}",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}