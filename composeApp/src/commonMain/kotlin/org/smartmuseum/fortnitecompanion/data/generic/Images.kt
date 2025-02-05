package org.smartmuseum.fortnitecompanion.data.generic

import kotlinx.serialization.Serializable

@Serializable
data class Images(
    val smallIcon: String? = null,
    val icon: String? = null,
    val featured: String? = null,
    val other: Map<String, String>? = null
)