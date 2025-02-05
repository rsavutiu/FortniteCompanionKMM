package org.smartmuseum.fortnitecompanion.di

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module
import org.smartmuseum.fortnitecompanion.storage.KMMStorage

@OptIn(ExperimentalCoroutinesApi::class)
actual val viewModelModule = module {
    single {
        KMMStorage(get())
    }
}