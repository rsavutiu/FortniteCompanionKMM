package org.smartmuseum.fortnitecompanion.utils

import org.smartmuseum.fortnitecompanion.storage.KMMSharedPrefs
import dev.icerock.moko.resources.StringResource
import io.ktor.utils.io.charsets.Charset

actual fun String.format(vararg args: String): String {
    return java.lang.String.format(this, *args)
}

actual class TextUtils actual constructor() {
    /**
     * Converts an ISO 3166-1 alpha-2 country code to the corresponding Unicode flag emoji.
     *
     * ```
     * "FR".countryCodeToUnicodeFlag() // 🇫🇷
     * "US".countryCodeToUnicodeFlag() // 🇺🇸
     * ```
     */
    actual fun countryCodeToUnicodeFlag(countryString: String): String {
        return countryString.uppercase()
            .filter { it in 'A'..'Z' }
            .map { it.code.toByte() }
            .flatMap { char ->
                listOf(
                    // First UTF-16 char is \uD83C
                    0xD8.toByte(),
                    0x3C.toByte(),
                    // Second char is \uDDE6 for A and increments from there
                    0xDD.toByte(),
                    (0xE6.toByte() + (char - 'A'.code.toByte())).toByte()
                )
            }
            .toByteArray()
            .let { bytes: ByteArray ->
                String(bytes, Charset.forName("UTF-16"))
            }
    }

    actual fun getString(sharedPrefs: KMMSharedPrefs, stringResource: StringResource): String {
        return stringResource.getString(sharedPrefs)
    }
}