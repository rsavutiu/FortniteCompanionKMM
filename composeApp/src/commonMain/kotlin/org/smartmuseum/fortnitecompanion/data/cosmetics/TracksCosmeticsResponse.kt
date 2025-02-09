package org.smartmuseum.fortnitecompanion.data.cosmetics

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import org.smartmuseum.fortnitecompanion.data.generic.CosmeticsSet
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
    val name: String,
    val description: String,
    val type: CosmeticType,
    val rarity: Rarity,
    val series: Series? = null,
    val set: CosmeticsSet? = null,
    val introduction: Introduction? = null,
    val images: TrackImages,
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
)

@Serializable
data class TrackImages(
    val smallIcon: String? = null,
    val icon: String? = null,
    val featured: String? = null,
    val background: String? = null,
    val other: Map<String, String>? = null
)

@Serializable
data class Difficulty(
    val vocals: Int? = null,
    val guitar: Int? = null,
    val bass: Int? = null,
    val plasticBass: Int? = null,
    val drums: Int? = null,
    val plasticDrums: Int? = null
)