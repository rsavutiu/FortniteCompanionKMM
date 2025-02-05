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
    val poi: List<Poi>? = null
)

@Serializable
data class MapImages(
    val blank: String,
    val pois: String
)

@Serializable
data class Poi(
    val id: String,
    val name: String,
    val location: Location
)

@Serializable
data class Location(
    val x: Int,
    val y: Int
)