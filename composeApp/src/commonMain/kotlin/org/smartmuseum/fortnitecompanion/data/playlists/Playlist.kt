package org.smartmuseum.fortnitecompanion.data.playlists

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Playlist(
    val id: String,
    val name: String,
    val description: String,
    val gameType: String? = null,
    val ratingType: String? = null,
    val minPlayers: Int,
    val maxPlayers: Int,
    val maxTeams: Int,
    val maxTeamSize: Int,
    val isDefault: Boolean,
    val isLimitedTimeMode: Boolean,
    val isTournament: Boolean,
    val image: String? = null,
    val path: String? = null,
    val added: Instant? = null
)