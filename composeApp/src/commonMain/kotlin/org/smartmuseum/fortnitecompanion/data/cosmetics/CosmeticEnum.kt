package org.smartmuseum.fortnitecompanion.data.cosmetics

import dev.icerock.moko.resources.StringResource
import org.smartmuseum.fortnitecompanion.resources

enum class CosmeticEnum(val stringResource: StringResource) {
    NEW(resources.strings.new_cosmetics),
    BATTLE_ROYALE(resources.strings.battle_royale_cosmetics),
    TRACKS(resources.strings.tracks_cosmetics),
    CARS(resources.strings.cars_cosmetics),
    BEANS(resources.strings.bean_cosmetics),
    INSTRUMENTS(resources.strings.instruments_cosmetics),
    LEGO(resources.strings.lego_cosmetics),
    LEGO_KITS(resources.strings.lego_kits_cosmetics)
}