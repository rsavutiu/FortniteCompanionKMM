package org.smartmuseum.fortnitecompanion.di

import org.koin.core.context.startKoin

actual class KoinInitializer {
    actual fun init() {
        startKoin {
            modules(commonModule, viewModelModule)
        }
    }
}