package org.smartmuseum.fortnitecompanion.data.generic

import kotlinx.serialization.Serializable

@Serializable
data class Introduction(
    val chapter: String,
    val season: String,
    val text: String,
    val backendValue: Int? = null
)