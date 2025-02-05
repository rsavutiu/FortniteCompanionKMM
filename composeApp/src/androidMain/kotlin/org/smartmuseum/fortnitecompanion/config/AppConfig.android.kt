package org.smartmuseum.fortnitecompanion.config

import org.smartmuseum.fortnitecompanion.BuildConfig

actual object AppConfig {
    actual val FORTNITE_API_KEY: String
        get() = BuildConfig.FORTNITE_API_KEY
}