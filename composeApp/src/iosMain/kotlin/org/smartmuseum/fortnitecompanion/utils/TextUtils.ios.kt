package org.smartmuseum.fortnitecompanion.utils

import androidx.compose.runtime.Composable
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.cstr
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.smartmuseum.fortnitecompanion.storage.KMMSharedPrefs
import platform.Foundation.NSString
import platform.Foundation.stringWithFormat

@OptIn(ExperimentalForeignApi::class)
actual fun String.format(vararg args: String): String {
    @Suppress("MagicNumber")
    return when (args.size) {
        0 -> NSString.stringWithFormat(this)
        1 -> NSString.stringWithFormat(this, args[0].cstr)
        2 -> NSString.stringWithFormat(this, args[0].cstr, args[1].cstr)
        3 -> NSString.stringWithFormat(this, args[0].cstr, args[1].cstr, args[2].cstr)
        4 -> NSString.stringWithFormat(this, args[0].cstr, args[1].cstr, args[2].cstr, args[3].cstr)
        5 -> NSString.stringWithFormat(
            this,
            args[0].cstr,
            args[1].cstr,
            args[2].cstr,
            args[3].cstr,
            args[4].cstr
        )

        else -> NSString.stringWithFormat(
            this,
            args[0].cstr,
            args[1].cstr,
            args[2].cstr,
            args[3].cstr,
            args[4].cstr,
            args[5].cstr
        )
    }
}

actual class TextUtils actual constructor() {
    actual fun countryCodeToUnicodeFlag(countryString: String): String {
        val base = 0x1F1E6 - 0x41

        var string = ""
        countryString.uppercase().forEach { chh: Char ->
            string += chh + base
        }

        return string
    }

    @Composable
    actual fun getString(sharedPrefs: KMMSharedPrefs, res: StringResource): String {
        return stringResource(res)
    }
}