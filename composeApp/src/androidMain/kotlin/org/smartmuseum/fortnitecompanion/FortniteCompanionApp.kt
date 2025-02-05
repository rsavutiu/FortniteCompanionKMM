package org.smartmuseum.fortnitecompanion

import android.app.Application
import android.os.StrictMode
import dev.icerock.moko.graphics.BuildConfig
import org.smartmuseum.fortnitecompanion.di.KoinInitializer

class FortniteCompanionApp: Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
        enableStrictMode()
        KoinInitializer(this@FortniteCompanionApp).init()
    }

    private fun enableStrictMode() {
        if (BuildConfig.DEBUG) { // Enable only in debug builds
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyLog() // Or .penaltyDeath() for more aggressive behavior
                    .build()
            )}
    }

    companion object {
        lateinit var appContext: Application
    }
}