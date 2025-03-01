package org.smartmuseum.fortnitecompanion.ui.screens.banners

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.smartmuseum.fortnitecompanion.data.cosmetics.BannerResponse
import org.smartmuseum.fortnitecompanion.data.cosmetics.ICosmetic
import org.smartmuseum.fortnitecompanion.ui.CosmeticItem

@Composable
fun BannersScreen(
    navController: NavController,
    bannerResponse: BannerResponse,
    onSelectCosmetic: (ICosmetic) -> Unit
) {
    LazyColumn {
        for (banner in bannerResponse.data) {
            item {
                CosmeticItem(
                    navController = navController,
                    fortniteCosmetic = banner,
                    useSmallIcon = false,
                    onSelectCosmetic = onSelectCosmetic
                )
            }
        }
    }
}