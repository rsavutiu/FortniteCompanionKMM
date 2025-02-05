package org.smartmuseum.fortnitecompanion.data.cosmetics

import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant
import org.smartmuseum.fortnitecompanion.data.generic.CosmeticsSet
import org.smartmuseum.fortnitecompanion.data.generic.Images
import org.smartmuseum.fortnitecompanion.data.generic.Introduction
import org.smartmuseum.fortnitecompanion.data.generic.Variant

@Serializable
data class CosmeticsResponse(
    val status: Int,
    val data: CosmeticsData)

@Serializable
data class CosmeticsData(
    val br: List<Cosmetic>,
    val tracks: List<TrackCosmetic>,
    val instruments: List<InstrumentCosmetic>,
    val cars: List<CarCosmetic>,
    val lego: List<LegoCosmetic>,
    val legoKits: List<LegoKitCosmetic>,
    val beans: List<BeanCosmetic>
)

@Serializable
data class Cosmetic(
    val id: String,
    val name: String? = null,
    val description: String? = null,
    val type: Type? = null,
    val rarity: Rarity? = null,
    val series: Series? = null,
    val set: CosmeticsSet? = null,
    val introduction: Introduction? = null,
    val images: Images? = null,
    val variants: List<Variant>? = null,
    val builtInEmoteIds: List<String>? = null,
    val builtInEmote: BuiltInEmote? = null,
    val gameplayTags: List<String>? = null,
    val metaTags: List<String>? = null,
    val showcaseVideo: String? = null,
    val dynamicPakId: String? = null,
    val itemPreviewHeroPath: String? = null,
    val displayAssetPath: String? = null,
    val definitionPath: String? = null,
    val path: String? = null,
    val added: Instant? = null,
    val shopHistory: List<String>? = null
)

@Serializable
data class Type(
    val value: String,
    val displayValue: String,
    val backendValue: String
)

@Serializable
data class Rarity(
    val value: String,
    val displayValue: String,
    val backendValue: String
)

@Serializable
data class Series(
    val value: String,
    val image: String? =null,
    val colors: List<String>? = null,
    val backendValue: String
)

@Serializable
data class Option(
    val tag: String,
    val name: String? = null,
    val unlockRequirements: String? = null,
    val image: String? = null
)

@Serializable
data class BuiltInEmote(
    val id: String,
    val name: String,
    val description: String,
    val type: Type,
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
    val shopHistory: List<String>? = null
)