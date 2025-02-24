package org.smartmuseum.fortnitecompanion.data.generic

import kotlinx.serialization.Serializable

@Serializable
data class Images(
    val smallIcon: String? = null,
    val small: String? = null,
    val icon: String? = null,
    val large: String? = null,
    val featured: String? = null,
    val background: String? = null,
    val other: Map<String, String>? = null
)