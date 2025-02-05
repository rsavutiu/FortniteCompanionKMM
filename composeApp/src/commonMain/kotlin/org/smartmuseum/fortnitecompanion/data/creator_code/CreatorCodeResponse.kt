package org.smartmuseum.fortnitecompanion.data.creator_code

import kotlinx.serialization.Serializable

@Serializable
data class CreatorCodeResponse(
    val status: Int,
    val data: CreatorCode?= null
)

@Serializable
data class CreatorCode(
    val code: String,
    val account: CreatorAccount,
    val slug: String? = null,
    val verified: Boolean,
    val lastUpdate: String
)

@Serializable
data class CreatorAccount(
    val id: String,
    val name: String
)