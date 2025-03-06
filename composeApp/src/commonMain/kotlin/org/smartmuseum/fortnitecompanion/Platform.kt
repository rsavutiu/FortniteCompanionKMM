package org.smartmuseum.fortnitecompanion

interface Platform {
    val name: String
    val type: String
}

expect fun getPlatform(): Platform