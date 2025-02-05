package org.smartmuseum.fortnitecompanion.data.cosmetics

import kotlinx.serialization.Serializable
import org.smartmuseum.fortnitecompanion.data.generic.Images

@Serializable
data class BannerResponse(
    val status: Int,
    val data: List<ItemData>)

@Serializable
data class ItemData(
    val id: String,
    val devName: String,
    val name: String,
    val description: String,
    val category: String,
    val fullUsageRights: Boolean,
    val images: Images
)
