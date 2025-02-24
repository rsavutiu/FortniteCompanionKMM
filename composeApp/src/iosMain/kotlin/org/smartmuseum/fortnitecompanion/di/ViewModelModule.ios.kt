package org.smartmuseum.fortnitecompanion.di

import org.koin.dsl.module
import org.smartmuseum.fortnitecompanion.storage.KMMStorage
import platform.darwin.NSObject

actual val viewModelModule = module {
    single {
        KMMStorage(NSObject())
    }
}