package org.smartmuseum.fortnitecompanion.data.playlists

import kotlinx.serialization.Serializable

@Serializable
data class PlaylistsResponse(
    val status: Int,
    val data: List<Playlist>
)