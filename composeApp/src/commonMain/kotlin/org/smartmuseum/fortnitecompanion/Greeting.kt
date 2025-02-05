package org.smartmuseum.fortnitecompanion

class Greeting(val platform: Platform = getPlatform()) {
    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}