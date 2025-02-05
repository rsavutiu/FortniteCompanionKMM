package org.smartmuseum.fortnitecompanion.data.cosmetics

import kotlinx.serialization.Serializable

@Serializable
data class BattleRoyaleCosmeticsResponse(
    val status: Int,
    val data: List<Cosmetic>
)