package org.smartmuseum.fortnitecompanion.data.generic

import kotlinx.serialization.Serializable
import org.smartmuseum.fortnitecompanion.data.cosmetics.Option

@Serializable
data class Variant(
    val channel: String? = null,
    val type: String? = null,
    val options: List<Option> = listOf()
)