package org.smartmuseum.fortnitecompanion.utils

import dev.icerock.moko.resources.StringResource
import org.smartmuseum.fortnitecompanion.storage.KMMSharedPrefs

expect fun String.format(format: String, vararg args: String): String
expect class TextUtils() {
    fun countryCodeToUnicodeFlag(countryString: String): String
    fun getString(sharedPrefs: KMMSharedPrefs, stringResource: StringResource): String
}