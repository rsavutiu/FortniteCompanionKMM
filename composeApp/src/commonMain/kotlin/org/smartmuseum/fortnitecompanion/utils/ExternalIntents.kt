package org.smartmuseum.fortnitecompanion.utils

import org.smartmuseum.fortnitecompanion.storage.KMMSharedPrefs

expect class ExternalIntents(kmmSharedPrefs: KMMSharedPrefs) {
    fun openSteam(steamId: String)
}