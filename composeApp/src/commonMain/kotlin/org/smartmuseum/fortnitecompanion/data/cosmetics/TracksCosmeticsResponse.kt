package org.smartmuseum.fortnitecompanion.data.cosmetics

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import org.smartmuseum.fortnitecompanion.data.generic.CosmeticsSet
import org.smartmuseum.fortnitecompanion.data.generic.Images
import org.smartmuseum.fortnitecompanion.data.generic.Introduction
import org.smartmuseum.fortnitecompanion.data.generic.Variant

@Serializable
data class TracksCosmeticsResponse(
    val status: Int,
    val data: List<TrackCosmetic>
)

@Serializable
data class TrackCosmetic(
    val id: String,
    val devName: String? = null,
    val title: String? = null,
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
    val artist: String? = null,
    val album: String? = null,
    val albumArt: String? = null,
    val releaseDate: String? = null,
    val duration: Int? = null,
    val bpm: Int? = null,
    val genres: List<String>? = null,
    val loudness: Double? = null,
    val energy: Double? = null,
    val danceability: Double? = null,
    val instrumentalness: Double? = null,
    val acousticness: Double? = null,
    val speechiness: Double? =null,
    val valence: Double? = null,
    val popularity: Int? = null,
    val difficulty: Difficulty? = null
) : ICosmetic {
    override fun getCosmeticsId(): String {
        return id
    }

    override fun getNameOrId(): String {
        return name ?: title ?: devName ?: id
    }

    override fun getCosmeticDescription(): String? {
        return description ?: artist
    }

    override fun getCosmeticRarity(): Rarity? {
        return rarity
    }

    override fun getSmallIcon(): String? {
        return images?.smallIcon ?: albumArt
    }

    override fun getIcon(): String? {
        return images?.icon ?: albumArt
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
data class Difficulty(
    val vocals: Int? = null,
    val guitar: Int? = null,
    val bass: Int? = null,
    val plasticBass: Int? = null,
    val drums: Int? = null,
    val plasticDrums: Int? = null
)