package org.smartmuseum.fortnitecompanion.data.cosmetics

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import org.smartmuseum.fortnitecompanion.data.generic.CosmeticsSet
import org.smartmuseum.fortnitecompanion.data.generic.Images
import org.smartmuseum.fortnitecompanion.data.generic.Introduction
import org.smartmuseum.fortnitecompanion.data.generic.Variant

@Serializable
data class LegoKitsCosmeticsResponse(
    val status: Int,
    val data: List<LegoKitCosmetic>
)

@Serializable
data class LegoKitCosmetic(
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
    val kitId: String? = null,
    val kitType: String? = null,
    val kitCategory: String? = null,
    val kitSubCategory: String? = null,
    val kitDescription: String? = null,
    val kitItems: List<KitItem>? = null
)

@Serializable
data class KitItem(
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
