package org.smartmuseum.fortnitecompanion.data.cosmetics

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class NewCosmeticsResponse(
    val status: Int,
    val data: NewCosmeticsData
)

@Serializable
data class NewCosmeticsData(
    val date: Instant,
    val build: String,
    val previousBuild: String,
    val hashes: Map<String, String>,
    val lastAdditions: Map<String, String>,
    val items: CosmeticsData
)