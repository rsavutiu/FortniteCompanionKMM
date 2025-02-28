package org.smartmuseum.fortnitecompanion.data.map

import kotlinx.serialization.Serializable

@Serializable
data class MapResponse(
    val status: Int,
    val data: MapData
)

@Serializable
data class MapData(
    val images: MapImages,
    val pois: List<Poi>? = null,
)

@Serializable
data class MapImages(
    val blank: String,
    val pois: String
)

@Serializable
data class Poi(
    val id: String,
    val name: String? = null,
    val location: Location? = null,
)

@Serializable
data class Location(
    val x: Float,
    val y: Float,
)