package org.smartmuseum.fortnitecompanion.di

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.module.Module
import org.koin.dsl.module
import org.smartmuseum.fortnitecompanion.storage.KMMStorage
import platform.darwin.NSObject

@OptIn(ExperimentalCoroutinesApi::class)
actual val viewModelModule: Module = module {
    single {
        KMMStorage(NSObject())
    }
}