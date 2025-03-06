package org.smartmuseum.fortnitecompanion.utils

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.smartmuseum.fortnitecompanion.storage.KMMSharedPrefs

expect fun String.format(vararg args: String): String
expect class TextUtils() {
    fun countryCodeToUnicodeFlag(countryString: String): String

    @Composable
    fun getString(sharedPrefs: KMMSharedPrefs, res: StringResource): String
}