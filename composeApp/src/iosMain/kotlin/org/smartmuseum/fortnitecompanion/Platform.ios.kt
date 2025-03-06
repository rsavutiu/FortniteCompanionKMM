package org.smartmuseum.fortnitecompanion

import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val type: String = "iOS"
}

actual fun getPlatform(): Platform = IOSPlatform()