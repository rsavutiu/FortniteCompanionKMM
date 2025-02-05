package org.smartmuseum.fortnitecompanion.data.playlists

import kotlinx.serialization.Serializable

@Serializable
data class SinglePlaylistResponse(
    val status: Int,
    val data: Playlist
)