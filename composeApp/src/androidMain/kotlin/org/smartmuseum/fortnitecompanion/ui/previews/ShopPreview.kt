package org.smartmuseum.fortnitecompanion.ui.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import org.smartmuseum.fortnitecompanion.data.shop.Difficulty
import org.smartmuseum.fortnitecompanion.data.shop.Layout
import org.smartmuseum.fortnitecompanion.data.shop.OfferTag
import org.smartmuseum.fortnitecompanion.data.shop.ShopData
import org.smartmuseum.fortnitecompanion.data.shop.ShopEntry
import org.smartmuseum.fortnitecompanion.data.shop.ShopResponse
import org.smartmuseum.fortnitecompanion.data.shop.Track
import org.smartmuseum.fortnitecompanion.ui.screens.shop.ShopScreen

fun getShopEntry(): ShopEntry =
    ShopEntry(
        regularPrice = 500,
        finalPrice = 1500,
        refundable = true,
        giftable = true,
        inDate = LocalDateTime(2024, 9, 23, 0, 0, 0).toInstant(TimeZone.UTC),
        outDate = LocalDateTime(2025, 3, 23, 0, 0, 0).toInstant(TimeZone.UTC),
        devName = "[VIRTUAL]1 x (Don't Fear) The Reaper for 500 MtxCurrency",
        offerId = "v2:/d6639757db82dc1a1bef324e2e7bef5d9b64206dc6ede81c7af08279de3f6548",
        newDisplayAssetPath = "sid_placeholder_20",
        tileSize = "Size_1_x_1",
        sortPriority = -3,
        layoutId = "Jamtracks0226.99",
        offerTag = OfferTag(
            id = "sparksjamloop",
            text = "Perform a Jam Track on the Festival Main Stage. Or, bust out your own Jam off the Main Stage with the Track's 4 Jam Loops!"
        ),
        layout = Layout(
            id = "Jamtracks0226",
            name = "Jam Tracks",
            category = "Take Your Stage",
            index = 28,
            rank = 186,
            showIneligibleOffers = "always",
            useWidePreview = false,
            displayType = "expandableList"
        ),
        tracks = listOf(
            Track(
                id = "sid_placeholder_20",
                devName = "dontfearthereaper",
                title = "(Don't Fear) The Reaper",
                artist = "Blue Öyster Cult",
                album = "Agents of Fortune",
                releaseYear = 1976,
                bpm = 141,
                duration = 322,
                difficulty = Difficulty(
                    vocals = 2,
                    guitar = 5,
                    bass = 5,
                    plasticBass = 2,
                    drums = 6,
                    plasticDrums = 5
                ),
                genres = listOf("Rock"),
                albumArt = "https://cdn.fortnite-api.com/tracks/ecbfcc89f0e78a68.png",
                added = LocalDateTime(2024, 3, 20, 0, 5, 45).toInstant(TimeZone.UTC)
            )
        )
    )

fun getMockShopResponse(): ShopResponse {
    return ShopResponse(
        status = 200,
        data = ShopData(
            hash = "mockHash",
            date = LocalDateTime(2024, 1, 25, 12, 0, 0).toInstant(TimeZone.UTC),
            entries = listOf(getShopEntry()),
            vbuckIcon = "https://fortnite-api.com/images/vbucks.png"
        )
    )
}

@Preview(showBackground = true)
@PreviewLightDark
@Composable
fun ShopPreview() {
    ShopScreen(
        ShopResponse(
            status = 200,
            data = getMockShopResponse().data
        )
    )
}