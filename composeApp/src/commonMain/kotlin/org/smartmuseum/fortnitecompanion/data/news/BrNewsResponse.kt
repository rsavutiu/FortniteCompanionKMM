package org.smartmuseum.fortnitecompanion.data.news

import kotlinx.serialization.Serializable

@Serializable
data class BrNewsResponse(
    val status: Int,val data: News
)