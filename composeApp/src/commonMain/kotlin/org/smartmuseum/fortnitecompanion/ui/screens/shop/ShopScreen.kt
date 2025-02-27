package org.smartmuseum.fortnitecompanion.ui.screens.shop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.smartmuseum.fortnitecompanion.data.shop.ShopResponse
import org.smartmuseum.fortnitecompanion.ui.molecules.ShopEntryItem
import org.smartmuseum.fortnitecompanion.ui.molecules.ShopHeader

@Composable
fun ShopScreen(shopResponse: ShopResponse) {
    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ShopHeader(shopData = shopResponse.data)
        }
        items(shopResponse.data.entries) { entry ->
            ShopEntryItem(entry = entry)
        }
    }
}
