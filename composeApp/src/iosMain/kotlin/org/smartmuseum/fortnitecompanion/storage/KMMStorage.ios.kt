package org.smartmuseum.fortnitecompanion.storage

import platform.darwin.NSObject

actual fun getStorage(context: KMMSharedPrefs): KMMStorage {
    return KMMStorage(NSObject())
}