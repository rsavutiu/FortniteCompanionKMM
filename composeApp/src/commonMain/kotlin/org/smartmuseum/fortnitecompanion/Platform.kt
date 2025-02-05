package org.smartmuseum.fortnitecompanion

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform