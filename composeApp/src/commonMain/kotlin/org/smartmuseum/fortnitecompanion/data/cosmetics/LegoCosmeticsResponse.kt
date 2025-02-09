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
    val name: String,
    val description: String,
    val type: CosmeticType,
    val rarity: Rarity,
    val series: Series? = null,
    val set: CosmeticsSet? = null,
    val introduction: Introduction? = null,
    val images: Images,
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
)

@Serializable
data class Style(
    val name: String,
    val channel: String,
    val images: Images? = null,
    val tag: String? = null
)
