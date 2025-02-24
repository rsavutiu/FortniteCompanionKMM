package org.smartmuseum.fortnitecompanion.di

import org.koin.dsl.module
import org.smartmuseum.fortnitecompanion.storage.KMMStorage

actual val viewModelModule = module {
    single {
        KMMStorage(get())
    }
}