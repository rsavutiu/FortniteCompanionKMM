package org.smartmuseum.fortnitecompanion.data.cosmetics

import fortnitecompanionapp.composeapp.generated.resources.Res
import fortnitecompanionapp.composeapp.generated.resources.battle_royale_cosmetics
import fortnitecompanionapp.composeapp.generated.resources.bean_cosmetics
import fortnitecompanionapp.composeapp.generated.resources.cars_cosmetics
import fortnitecompanionapp.composeapp.generated.resources.instruments_cosmetics
import fortnitecompanionapp.composeapp.generated.resources.lego_cosmetics
import fortnitecompanionapp.composeapp.generated.resources.lego_kits_cosmetics
import fortnitecompanionapp.composeapp.generated.resources.new_cosmetics
import fortnitecompanionapp.composeapp.generated.resources.tracks_cosmetics
import org.jetbrains.compose.resources.StringResource


enum class CosmeticEnum(val stringResource: StringResource) {
    NEW(Res.string.new_cosmetics),
    BATTLE_ROYALE(Res.string.battle_royale_cosmetics),
    TRACKS(Res.string.tracks_cosmetics),
    CARS(Res.string.cars_cosmetics),
    BEANS(Res.string.bean_cosmetics),
    INSTRUMENTS(Res.string.instruments_cosmetics),
    LEGO(Res.string.lego_cosmetics),
    LEGO_KITS(Res.string.lego_kits_cosmetics)
}