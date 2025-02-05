package org.smartmuseum.fortnitecompanion.config
import platform.Foundation.NSBundle
actual object AppConfig {
    actual val FORTNITE_API_KEY: String = NSBundle.mainBundle.infoDictionary?.get("FORTNITE_API_KEY") as? String ?: ""
}