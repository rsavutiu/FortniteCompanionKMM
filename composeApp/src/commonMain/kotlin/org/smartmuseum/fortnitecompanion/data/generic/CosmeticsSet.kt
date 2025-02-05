package org.smartmuseum.fortnitecompanion.data.generic

import kotlinx.serialization.Serializable

@Serializable
data class CosmeticsSet(
    val value: String,
    val text: String,
    val backendValue: String
)