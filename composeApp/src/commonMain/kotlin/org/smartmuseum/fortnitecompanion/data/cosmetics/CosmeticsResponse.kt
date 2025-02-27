package org.smartmuseum.fortnitecompanion.data.cosmetics

import dev.icerock.moko.resources.ImageResource
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import org.smartmuseum.fortnitecompanion.data.generic.CosmeticsSet
import org.smartmuseum.fortnitecompanion.data.generic.Images
import org.smartmuseum.fortnitecompanion.data.generic.Introduction
import org.smartmuseum.fortnitecompanion.data.generic.Variant
import org.smartmuseum.fortnitecompanion.resources

@Serializable
data class CosmeticsResponse(
    val status: Int,
    val data: CosmeticsData)

@Serializable
data class CosmeticsData(
    val br: List<Cosmetic>? = listOf(),
    val tracks: List<TrackCosmetic>? = listOf(),
    val instruments: List<InstrumentCosmetic>? = listOf(),
    val cars: List<CarCosmetic>? = listOf(),
    val lego: List<LegoCosmetic>? = listOf(),
    val legoKits: List<LegoKitCosmetic>? = listOf(),
    val beans: List<BeanCosmetic>? = listOf()
) {
    fun getCosmetics(): List<ICosmetic> {
        val cosmetics = mutableListOf<ICosmetic>()
        br?.let { cosmetics.addAll(it) }
        tracks?.let { cosmetics.addAll(it) }
        instruments?.let { cosmetics.addAll(it) }
        cars?.let { cosmetics.addAll(it) }
        lego?.let { cosmetics.addAll(it) }
        legoKits?.let { cosmetics.addAll(it) }
        return cosmetics
    }
}

interface ICosmetic {
    fun getCosmeticsId(): String
    fun getNameOrId(): String
    fun getCosmeticDescription(): String?
    fun getCosmeticRarity(): Rarity?
    fun getSmallIcon(): String?
    fun getIcon(): String?
    fun getCosmeticIntroduction(): Introduction?
    fun getCosmeticType(): CosmeticType?
    fun getVideo(): String?
    fun getDate(): Instant?

    fun getVideoUrl(): String? {
        getVideo()?.let { showcaseVideo ->
            return "https://www.youtube.com/watch?v=$showcaseVideo"
        }
        return null
    }
}

@Serializable
data class Cosmetic(
    val id: String,
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
) : ICosmetic {
    override fun getCosmeticsId(): String {
        return id
    }

    override fun getNameOrId(): String {
        return name ?: id
    }

    override fun getCosmeticDescription(): String? {
        return description
    }

    override fun getCosmeticRarity(): Rarity? {
        return rarity
    }

    override fun getIcon(): String? {
        return images?.icon
    }

    override fun getCosmeticIntroduction(): Introduction? {
        return introduction
    }

    override fun getSmallIcon(): String? {
        return images?.smallIcon
    }

    override fun getCosmeticType(): CosmeticType? {
        return type
    }

    override fun getVideo(): String? {
        return showcaseVideo ?: builtInEmote?.showcaseVideo
    }

    override fun getDate(): Instant? {
        return added
    }
}

@Serializable
data class CosmeticType(
    val value: String,
    val displayValue: String,
    val backendValue: String
) {
    fun getCosmeticTypeImage(): ImageResource? {
        when (value.lowercase()) {
            "backpack" -> return resources.images.back_blings_icon
            "emote", "built-in emote" -> return resources.images.emotes_icon
            "emoticon", "emoji" -> return resources.images.emoticons_icon
            "emotes" -> return resources.images.emotes_icon
            "outfit", "skin" -> return resources.images.outfit_icon
            "loadingscreen" -> return resources.images.loading_screens_icon
            "spray" -> return resources.images.sprays_icon
            "contrail" -> return resources.images.contrail_icon
            "pickaxe" -> return resources.images.pickaxe_icon
            "wrap", "wraps" -> return resources.images.wraps_icon
            "glider" -> return resources.images.glider_icon
            "lobbymusic", "music" -> return resources.images.lobby_music_icon
            "banner" -> return resources.images.banner_icon
            "trail", "turbo", "drifttrail", "drift" -> return resources.images.trail_icon
            "wheel" -> return resources.images.wheel_icon
            "aura" -> return resources.images.aura_icon
            "toy" -> return resources.images.toys_icon
            "shoe", "shoes", "kicks" -> return resources.images.kicks_icon
            "petcarrier", "pet" -> return resources.images.pet_icon
            "bundle" -> return resources.images.bundle_icon
            "guitar" -> return resources.images.guitar_icon
            "keytar", "keyboard" -> return resources.images.keytar_icon
            "bass" -> return resources.images.bass_icon
            "drum", "drums" -> return resources.images.drums_icon
            "mic", "microphone" -> return resources.images.microphone_icon
            "booster" -> return resources.images.boost_icon
            "body" -> return resources.images.car_body_icon
            "decor", "legoprop" -> return resources.images.decorations_icon_lego
            "build", "legoset" -> return resources.images.builds_icon_lego
        }
        return null
    }
}

@Serializable
data class Rarity(
    val value: String,
    val displayValue: String,
    val backendValue: String
)

@Serializable
data class Series(
    val value: String,
    val displayValue: String? = null,
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
    val shopHistory: List<String>? = null
)