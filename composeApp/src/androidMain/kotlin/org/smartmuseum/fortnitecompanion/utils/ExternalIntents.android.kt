package org.smartmuseum.fortnitecompanion.utils

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import org.smartmuseum.fortnitecompanion.storage.KMMSharedPrefs

actual class ExternalIntents actual constructor(private val kmmSharedPrefs: KMMSharedPrefs) {
    actual fun openSteam(steamId: String) {
        val url = "https://steamcommunity.com/profiles/$steamId"
        val i: Intent = Intent(Intent.ACTION_VIEW)
        i.setData(Uri.parse(url))
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(kmmSharedPrefs, i, null)
    }
}