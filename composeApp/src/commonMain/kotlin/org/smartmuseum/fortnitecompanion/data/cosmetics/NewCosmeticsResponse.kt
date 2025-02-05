package org.smartmuseum.fortnitecompanion.data.cosmetics

import kotlinx.serialization.Serializable

@Serializable
data class NewCosmeticsResponse(
    val status: Int,
    val data: NewCosmeticsData
)

@Serializable
data class NewCosmeticsData(
    val br: List<Cosmetic>,
    val tracks: List<Cosmetic>,
    val instruments: List<Cosmetic>,
    val cars: List<Cosmetic>,
    val lego: List<Cosmetic>,
    val legoKits: List<Cosmetic>,
    val beans: List<Cosmetic>
)