package org.smartmuseum.fortnitecompanion.data.cosmetics

import androidx.compose.runtime.State
import org.smartmuseum.fortnitecompanion.networking.NetworkResult

data class CosmeticsUiData(
    val title: String,
    val refresh: () -> Unit,
    val responseState: State<NetworkResult<List<ICosmetic>>>,
    val cosmeticsEnum: CosmeticEnum
)