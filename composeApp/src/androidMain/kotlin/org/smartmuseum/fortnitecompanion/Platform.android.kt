package org.smartmuseum.fortnitecompanion

import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val type: String = "Android"
}

actual fun getPlatform(): Platform = AndroidPlatform()