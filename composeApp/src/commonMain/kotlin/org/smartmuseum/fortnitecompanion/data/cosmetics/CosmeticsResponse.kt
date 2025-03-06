package org.smartmuseum.fortnitecompanion.data.cosmetics

import fortnitecompanionapp.composeapp.generated.resources.Res
import fortnitecompanionapp.composeapp.generated.resources.aura_icon
import fortnitecompanionapp.composeapp.generated.resources.back_blings_icon
import fortnitecompanionapp.composeapp.generated.resources.banner_icon
import fortnitecompanionapp.composeapp.generated.resources.bass_icon
import fortnitecompanionapp.composeapp.generated.resources.boost_icon
import fortnitecompanionapp.composeapp.generated.resources.builds_icon_lego
import fortnitecompanionapp.composeapp.generated.resources.bundle_icon
import fortnitecompanionapp.composeapp.generated.resources.car_body_icon
import fortnitecompanionapp.composeapp.generated.resources.contrail_icon
import fortnitecompanionapp.composeapp.generated.resources.decorations_icon_lego
import fortnitecompanionapp.composeapp.generated.resources.drums_icon
import fortnitecompanionapp.composeapp.generated.resources.emotes_icon
import fortnitecompanionapp.composeapp.generated.resources.emoticons_icon
import fortnitecompanionapp.composeapp.generated.resources.glider_icon
import fortnitecompanionapp.composeapp.generated.resources.guitar_icon
import fortnitecompanionapp.composeapp.generated.resources.keytar_icon
import fortnitecompanionapp.composeapp.generated.resources.kicks_icon
import fortnitecompanionapp.composeapp.generated.resources.loading_screens_icon
import fortnitecompanionapp.composeapp.generated.resources.lobby_music_icon
import fortnitecompanionapp.composeapp.generated.resources.microphone_icon
import fortnitecompanionapp.composeapp.generated.resources.outfit_icon
import fortnitecompanionapp.composeapp.generated.resources.pet_icon
import fortnitecompanionapp.composeapp.generated.resources.pickaxe_icon
import fortnitecompanionapp.composeapp.generated.resources.sprays_icon
import fortnitecompanionapp.composeapp.generated.resources.toys_icon
import fortnitecompanionapp.composeapp.generated.resources.trail_icon
import fortnitecompanionapp.composeapp.generated.resources.wheel_icon
import fortnitecompanionapp.composeapp.generated.resources.wraps_icon
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource
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
    fun getCosmeticTypeImage(): DrawableResource? {
        when (value.lowercase()) {
            "backpack" -> return Res.drawable.back_blings_icon
            "emote", "built-in emote" -> return Res.drawable.emotes_icon
            "emoticon", "emoji" -> return Res.drawable.emoticons_icon
            "emotes" -> return Res.drawable.emotes_icon
            "outfit", "skin" -> return Res.drawable.outfit_icon
            "loadingscreen" -> return Res.drawable.loading_screens_icon
            "spray" -> return Res.drawable.sprays_icon
            "contrail" -> return Res.drawable.contrail_icon
            "pickaxe" -> return Res.drawable.pickaxe_icon
            "wrap", "wraps" -> return Res.drawable.wraps_icon
            "glider" -> return Res.drawable.glider_icon
            "lobbymusic", "music" -> return Res.drawable.lobby_music_icon
            "banner" -> return Res.drawable.banner_icon
            "trail", "turbo", "drifttrail", "drift" -> return Res.drawable.trail_icon
            "wheel" -> return Res.drawable.wheel_icon
            "aura" -> return Res.drawable.aura_icon
            "toy" -> return Res.drawable.toys_icon
            "shoe", "shoes", "kicks" -> return Res.drawable.kicks_icon
            "petcarrier", "pet" -> return Res.drawable.pet_icon
            "bundle" -> return Res.drawable.bundle_icon
            "guitar" -> return Res.drawable.guitar_icon
            "keytar", "keyboard" -> return Res.drawable.keytar_icon
            "bass" -> return Res.drawable.bass_icon
            "drum", "drums" -> return Res.drawable.drums_icon
            "mic", "microphone" -> return Res.drawable.microphone_icon
            "booster" -> return Res.drawable.boost_icon
            "body" -> return Res.drawable.car_body_icon
            "decor", "legoprop" -> return Res.drawable.decorations_icon_lego
            "build", "legoset" -> return Res.drawable.builds_icon_lego
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