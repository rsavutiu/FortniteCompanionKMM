package org.smartmuseum.fortnitecompanion.data.news

import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    val status: Int,
    val data: NewsData
)

@Serializable
data class NewsData(
    val br: News,
    val creative: News? = null,
    val stw: News? = null
)

@Serializable
data class News(
    val hash: String,
    val date: String,
    val image: String,
    val motds: List<Motd>
)

@Serializable
data class Motd(
    val id: String,
    val title: String,
    val body: String,
    val image: String,
    val tileImage: String? = null,
    val hidden: Boolean
)