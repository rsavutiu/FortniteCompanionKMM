package org.smartmuseum.fortnitecompanion.networking

import androidx.compose.ui.text.intl.Locale

class FortniteAPISupportedLanguages {
    private val supportedLanguages: List<String> = listOf(
        "ar",
        "de",
        "en",
        "es",
        "es-419",
        "fr",
        "it",
        "ja",
        "ko",
        "pl",
        "pt-BR",
        "ru",
        "tr",
        "zh-Hans",
        "zh-Hant"
    )

    var supportedLanguage: String?

    init {
        supportedLanguage = getSupportedLanguage(Locale.current.language)
    }

    private fun getSupportedLanguage(language: String): String? {
        for (candidate in supportedLanguages) {
            if (candidate.startsWith(language)) {
                return candidate
            }
        }
        return null
    }
}