package org.smartmuseum.fortnitecompanion.ui.previews

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import kotlinx.datetime.Instant
import org.smartmuseum.fortnitecompanion.data.cosmetics.Cosmetic
import org.smartmuseum.fortnitecompanion.data.cosmetics.CosmeticType
import org.smartmuseum.fortnitecompanion.data.cosmetics.Rarity
import org.smartmuseum.fortnitecompanion.data.cosmetics.Series
import org.smartmuseum.fortnitecompanion.data.generic.CosmeticsSet
import org.smartmuseum.fortnitecompanion.data.generic.Images
import org.smartmuseum.fortnitecompanion.data.generic.Introduction
import org.smartmuseum.fortnitecompanion.ui.screens.cosmetics.CosmeticsList
import org.smartmuseum.fortnitecompanion.ui.themes.AppTheme

@Preview
@Composable
fun CosmeticsListPreview() {
    AppTheme {
        CosmeticsList(
            modifier = Modifier.fillMaxSize(),
            navController = rememberNavController(),
            cosmeticsMap = hashMapOf(
                "gaminglegends" to listOf(
                    Cosmetic(
                        id = "aBackpack_AbstractMirror",
                        name = "Metal Gear Mk. II",
                        description = "Backs you up on the battlefield.",
                        type = CosmeticType(
                            value = "backpack",
                            displayValue = "Back Bling",
                            backendValue = "AthenaBackpack"
                        ),
                        rarity = Rarity(
                            value = "gaminglegends",
                            displayValue = "Gaming Legends Series",
                            backendValue = "EFortRarity::Epic"
                        ),
                        series = Series(
                            value = "Gaming Legends Series",
                            backendValue = "PlatformSeries"
                        ),
                        set = CosmeticsSet(
                            value = "Sneaking",
                            text = "Part of the Sneaking set.",
                            backendValue = "HidingTime"
                        ),
                        introduction = Introduction(
                            chapter = "5",
                            season = "1",
                            text = "Introduced in Chapter 5, Season 1.",
                            backendValue = 28
                        ),
                        images = Images(
                            smallIcon = "https://fortnite-api.com/images/cosmetics/br/backpack_abstractmirror/smallicon.png",
                            icon = "https://fortnite-api.com/images/cosmetics/br/backpack_abstractmirror/icon.png"
                        ),
                        metaTags = listOf("Cosmetics.Metatags.AbstractMirror"),
                        added = Instant.fromEpochMilliseconds(1706016888000L)
                    ),
                    Cosmetic(
                        id = "Backpack_CrimsonPeak",
                        name = "RIG",
                        description = "Watch your back with a personalized life-monitoring system.",
                        type = CosmeticType(
                            value = "backpack",
                            displayValue = "Back Bling",
                            backendValue = "AthenaBackpack"
                        ),
                        rarity = Rarity(
                            value = "gaminglegends",
                            displayValue = "Gaming Legends Series",
                            backendValue = "EFortRarity::Epic"
                        ),
                        series = Series(
                            value = "Gaming Legends Series",
                            backendValue = "PlatformSeries"
                        ),
                        set = CosmeticsSet(
                            value = "Sneaking",
                            text = "Part of the Sneaking set.",
                            backendValue = "HidingTime"
                        ),
                        introduction = Introduction(
                            chapter = "4",
                            season = "1",
                            text = "Introduced in Chapter 4, Season 1.",
                            backendValue = 23
                        ),
                        images = Images(
                            smallIcon = "https://fortnite-api.com/images/cosmetics/br/backpack_crimsonpeak/smallicon.png",
                            icon = "https://fortnite-api.com/images/cosmetics/br/backpack_crimsonpeak/icon.png"
                        ),
                        metaTags = listOf("Cosmetics.Metatags.AbstractMirror"),
                        added = Instant.fromEpochMilliseconds(1674511767000L)
                    )
                ), "Rare" to listOf(
                    Cosmetic(
                        id = "Backpack_AccentWall",
                        name = "Lik Kraken",
                        description = "Awww, he just woke up!",
                        type = CosmeticType(
                            value = "backpack",
                            displayValue = "Back Bling",
                            backendValue = "AthenaBackpack"
                        ),
                        rarity = Rarity(
                            value = "rare",
                            displayValue = "Rare",
                            backendValue = "EFortRarity::Rare"
                        ),
                        series = Series(
                            value = "Gaming Legends Series",
                            backendValue = "PlatformSeries"
                        ),
                        set = CosmeticsSet(
                            value = "Pirates of the Caribbean",
                            text = "Part of the Pirates of the Caribbean set.",
                            backendValue = "GroanScheme"
                        ),
                        introduction = Introduction(
                            chapter = "5",
                            season = "3",
                            text = "Introduced in Chapter 5, Season 3.",
                            backendValue = 30
                        ),
                        images = Images(
                            smallIcon = "https://fortnite-api.com/images/cosmetics/br/backpack_accentwall/smallicon.png",
                            icon = "https://fortnite-api.com/images/cosmetics/br/backpack_accentwall/icon.png"
                        ),
                        added = Instant.fromEpochMilliseconds(1721174400)
                    )
                )
            )
        )
    }
}
