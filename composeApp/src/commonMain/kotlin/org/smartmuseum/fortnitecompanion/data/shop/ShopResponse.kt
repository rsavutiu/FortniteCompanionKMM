package org.smartmuseum.fortnitecompanion.data.shop

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShopResponse(
    val status: Int,
    val data: ShopData
)

@Serializable
data class ShopData(
    val hash: String,
    val date: Instant,
    val vbuckIcon: String,
    val entries: List<ShopEntry>
)

@Serializable
data class ShopEntry(
    val regularPrice: Int,
    val finalPrice: Int,
    val devName: String,
    val offerId: String,
    val inDate: Instant,
    val outDate: Instant,
    val offerTag: OfferTag? = null,
    val giftable: Boolean,
    val refundable: Boolean,
    val sortPriority: Int,
    val layoutId: String,
    val layout: Layout? = null,
    val tileSize: String? = null,
    val newDisplayAssetPath: String? = null,
    val newDisplayAsset: DisplayAsset? = null,
    val tracks: List<Track>? = null
)

@Serializable
data class OfferTag(
    val id: String,
    val text: String
)

@Serializable
data class RenderImage(
    val productTag: String? = null,
    val fileName: String? = null,
    val image: String? = null
)

@Serializable
data class DisplayAsset(
    val id: String,
    val renderImages: List<RenderImage>? = null
)

@Serializable
data class Layout(
    val id: String? = null,
    val name: String? = null,
    val category: String? = null,
    val index: Int? = null,
    val rank: Int? = null,
    val showIneligibleOffers: String? = null,
    val useWidePreview: Boolean? = null,
    val displayType: String? = null
)

@Serializable
data class Track(
    val id: String,
    val devName: String? = null,
    val title: String? = null,
    val artist: String? = null,
    val album: String? = null,
    val releaseYear: Int? = null,
    val bpm: Int? = null,
    val duration: Int? = null,
    val difficulty: Difficulty? = null,
    val genres: List<String> = emptyList(),
    val albumArt: String? = null,
    val added: Instant
)

@Serializable
data class Difficulty(
    val vocals: Int,
    val guitar: Int,
    val bass: Int,
    @SerialName("plasticBass") val plasticBass: Int,
    val drums: Int,
    @SerialName("plasticDrums") val plasticDrums: Int
)