package org.smartmuseum.fortnitecompanion.ui.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import kotlinx.datetime.Instant
import org.smartmuseum.fortnitecompanion.data.cosmetics.Cosmetic
import org.smartmuseum.fortnitecompanion.data.cosmetics.CosmeticType
import org.smartmuseum.fortnitecompanion.data.cosmetics.Rarity
import org.smartmuseum.fortnitecompanion.data.generic.CosmeticsSet
import org.smartmuseum.fortnitecompanion.data.generic.Images
import org.smartmuseum.fortnitecompanion.data.generic.Introduction
import org.smartmuseum.fortnitecompanion.ui.molecules.CosmeticItem
import org.smartmuseum.fortnitecompanion.ui.themes.AppTheme

@Preview(showBackground = true)
@Composable
fun FortniteCosmeticPreview() {
    AppTheme {
        CosmeticItem(
            navController = rememberNavController(),
            fortniteCosmetic =
            Cosmetic(
                id = "Backpack_ApplePound",
                name = "Playful Cloud",
                description = "A cursed tool of deadly capabilities.",
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
                set = CosmeticsSet(
                    value = "Jujutsu Kaisen",
                    text = "Part of the Jujutsu Kaisen set.",
                    backendValue = "CleanWard"
                ),
                introduction = Introduction(
                    chapter = "6",
                    season = "1",
                    text = "Introduced in Chapter 6, Season 1.",
                    backendValue = 33
                ),
                images = Images(
                    smallIcon = "https://fortnite-api.com/images/cosmetics/br/backpack_applepound/smallicon.png",
                    icon = "https://fortnite-api.com/images/cosmetics/br/backpack_applepound/icon.png"
                ),
                metaTags = listOf("Cosmetics.MetaTags.ApplePound"),
                showcaseVideo = "",
                dynamicPakId = "1003",
                added = Instant.fromEpochSeconds(epochSeconds = 1739102970)
            )
        )
    }
}