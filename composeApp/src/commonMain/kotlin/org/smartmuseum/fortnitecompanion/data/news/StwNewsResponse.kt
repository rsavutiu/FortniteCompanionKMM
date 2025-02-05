package org.smartmuseum.fortnitecompanion.data.news

import kotlinx.serialization.Serializable

@Serializable
data class StwNewsResponse(
    val status: Int,
    val data: News
)
