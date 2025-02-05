package org.smartmuseum.fortnitecompanion.utils

import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.desc
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.cstr
import org.smartmuseum.fortnitecompanion.storage.KMMSharedPrefs
import platform.Foundation.NSString
import platform.Foundation.stringWithFormat

@OptIn(ExperimentalForeignApi::class)
actual fun String.format(format:String, vararg args: String): String {
    @Suppress("MagicNumber")
    return when (args.size) {
        0 -> NSString.stringWithFormat(format)
        1 -> NSString.stringWithFormat(format, args[0].cstr)
        2 -> NSString.stringWithFormat(format, args[0].cstr, args[1].cstr)
        3 -> NSString.stringWithFormat(format, args[0].cstr, args[1].cstr, args[2].cstr)
        4 -> NSString.stringWithFormat(format, args[0].cstr, args[1].cstr, args[2].cstr, args[3].cstr)
        else -> NSString.stringWithFormat(format, args[0].cstr, args[1].cstr, args[2].cstr, args[3].cstr, args[4].cstr)
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

    actual fun getString(sharedPrefs: KMMSharedPrefs, stringResource: StringResource): String {
        return stringResource.desc().localized()
    }
}