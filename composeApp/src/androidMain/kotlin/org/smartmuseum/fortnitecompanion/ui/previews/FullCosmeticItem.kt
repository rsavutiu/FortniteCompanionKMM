package org.smartmuseum.fortnitecompanion.ui.previews

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.datetime.Instant
import org.smartmuseum.fortnitecompanion.data.cosmetics.Cosmetic
import org.smartmuseum.fortnitecompanion.data.cosmetics.CosmeticType
import org.smartmuseum.fortnitecompanion.data.cosmetics.Rarity
import org.smartmuseum.fortnitecompanion.data.cosmetics.Series
import org.smartmuseum.fortnitecompanion.data.generic.CosmeticsSet
import org.smartmuseum.fortnitecompanion.data.generic.Images
import org.smartmuseum.fortnitecompanion.data.generic.Introduction
import org.smartmuseum.fortnitecompanion.ui.screens.cosmetics.FullCosmeticScreen
import org.smartmuseum.fortnitecompanion.ui.themes.AppTheme

@Preview
@Composable
fun FullCosmeticPreview() {
    AppTheme {
        FullCosmeticScreen(
            fullCosmeticState = remember {
                mutableStateOf(
                    Cosmetic(
                        id = "Backpack_AbstractMirror",
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
                    )
                )
            }
        )
    }
}
