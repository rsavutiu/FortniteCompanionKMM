package org.smartmuseum.fortnitecompanion.storage

actual fun getStorage(context: KMMSharedPrefs): KMMStorage {
    return KMMStorage(context)
}