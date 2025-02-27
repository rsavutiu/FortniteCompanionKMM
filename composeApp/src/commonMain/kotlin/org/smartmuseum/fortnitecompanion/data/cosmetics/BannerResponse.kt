package org.smartmuseum.fortnitecompanion.data.cosmetics

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import org.smartmuseum.fortnitecompanion.data.generic.Images
import org.smartmuseum.fortnitecompanion.data.generic.Introduction

@Serializable
data class BannerResponse(
    val status: Int,
    val data: List<ItemData>)

@Serializable
data class ItemData(
    val id: String,
    val devName: String? = null,
    val name: String? = null,
    val description: String? = null,
    val category: String? = null,
    val fullUsageRights: Boolean = true,
    val images: Images
) : ICosmetic {
    override fun getCosmeticsId(): String {
        return id
    }

    override fun getNameOrId(): String {
        return name ?: devName ?: id
    }

    override fun getCosmeticDescription(): String? {
        return description
    }

    override fun getCosmeticRarity(): Rarity? {
        return null
    }

    override fun getSmallIcon(): String? {
        return images.smallIcon
    }

    override fun getIcon(): String? {
        return images.icon ?: images.smallIcon
    }

    override fun getCosmeticIntroduction(): Introduction? {
        return null
    }

    override fun getCosmeticType(): CosmeticType? {
        return null
    }

    override fun getVideo(): String? {
        return null
    }

    override fun getDate(): Instant? {
        return null
    }

}
