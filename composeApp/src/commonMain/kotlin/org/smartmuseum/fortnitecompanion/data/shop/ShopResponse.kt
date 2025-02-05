package org.smartmuseum.fortnitecompanion.data.shop

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import org.smartmuseum.fortnitecompanion.data.generic.Images
import org.smartmuseum.fortnitecompanion.data.generic.Variant
import org.smartmuseum.fortnitecompanion.data.cosmetics.Rarity
import org.smartmuseum.fortnitecompanion.data.cosmetics.Series
import org.smartmuseum.fortnitecompanion.data.cosmetics.Type
import org.smartmuseum.fortnitecompanion.data.generic.CosmeticsSet
import org.smartmuseum.fortnitecompanion.data.generic.Introduction

@Serializable
data class ShopResponse(
    val status: Int,
    val data: ShopData
)

@Serializable
data class ShopData(
    val hash: String,
    val date: Instant,
    val featured: ShopSection? = null,
    val daily: ShopSection? = null,
    val specialFeatured: ShopSection? = null,
    val specialDaily: ShopSection? = null,
    val votes: ShopSection? = null,
    val voteWinners: ShopSection? = null,
    val upcoming: List<ShopEntry>? = null
)

@Serializable
data class ShopSection(
    val name: String,
    val entries: List<ShopEntry>
)

@Serializable
data class ShopEntry(
    val regularPrice: Int,
    val finalPrice: Int,
    val bundle: Bundle? = null,
    val banner: Banner? = null,
    val displayAssetPath: String? = null,
    val definitionPath: String? = null,
    val offerId: String? = null,
    val devName: String? = null,
    val offerType: String? = null,
    val categories: List<String>? = null,
    val items: List<ShopItem>
)

@Serializable
data class ShopItem(
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

@Serializable
data class Bundle(
    val name: String,
    val info: String? = null,
    val image: String? = null
)

@Serializable
data class Banner(
    val value: String,
    val intensity: String,
    val backendValue: String
)