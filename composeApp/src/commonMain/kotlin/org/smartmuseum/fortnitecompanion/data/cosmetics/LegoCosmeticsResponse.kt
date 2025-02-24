package org.smartmuseum.fortnitecompanion.data.cosmetics

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import org.smartmuseum.fortnitecompanion.data.generic.CosmeticsSet
import org.smartmuseum.fortnitecompanion.data.generic.Images
import org.smartmuseum.fortnitecompanion.data.generic.Introduction
import org.smartmuseum.fortnitecompanion.data.generic.Variant

@Serializable
data class LegoCosmeticsResponse(
    val status: Int,
    val data: List<LegoCosmetic>
)

@Serializable
data class LegoCosmetic(
    val id: String,
    val cosmeticId: String? = null,
    val name: String? = null,
    val description: String? = null,
    val type: CosmeticType? = null,
    val rarity: Rarity? = null,
    val series: Series? = null,
    val set: CosmeticsSet? = null,
    val introduction: Introduction? = null,
    val images: Images? = null,
    val variants: List<Variant>? = null,
    val builtInEmoteIds: List<String>? = null,
    val gameplayTags: List<String>? = null,
    val metaTags: List<String>? = null,
    val showcaseVideo: String? = null,
    val dynamicPakId: String? = null,
    val itemPreviewHeroPath: String? = null,
    val displayAssetPath: String? = null,
    val definitionPath: String? = null,
    val path: String? = null,
    val added: Instant? = null,
    val shopHistory: List<String>? = null,
    val grantedFor: String? = null,
    val styles: List<Style>? = null
) : ICosmetic {
    override fun getCosmeticsId(): String {
        return id
    }

    override fun getNameOrId(): String {
        return name ?: cosmeticId ?: id
    }

    override fun getCosmeticDescription(): String? {
        return description
    }

    override fun getCosmeticRarity(): Rarity? {
        return rarity
    }

    override fun getSmallIcon(): String? {
        return images?.smallIcon ?: images?.small
    }

    override fun getIcon(): String? {
        return images?.icon ?: images?.large ?: getSmallIcon()
    }

    override fun getCosmeticIntroduction(): Introduction? {
        return introduction
    }

    override fun getCosmeticType(): CosmeticType? {
        return type
    }

    override fun getVideo(): String? {
        return showcaseVideo
    }

    override fun getDate(): Instant? {
        return added
    }
}

@Serializable
data class Style(
    val name: String,
    val channel: String,
    val images: Images? = null,
    val tag: String? = null
)
