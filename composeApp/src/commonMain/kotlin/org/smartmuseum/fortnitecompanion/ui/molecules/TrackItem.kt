package org.smartmuseum.fortnitecompanion.ui.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.smartmuseum.fortnitecompanion.data.shop.Track

@Composable
fun TrackItem(track: Track) {
    Row(modifier = Modifier.fillMaxWidth()) {
        track.albumArt?.let { albumArt ->
            AsyncImage(
                modifier = Modifier.fillMaxWidth(0.4f),
                model = albumArt,
                contentDescription = null
            )
        }
        Column(modifier = Modifier.padding(16.dp)) {
            track.title?.let { trackTitle ->
                Text(
                    text = trackTitle,
                    style = MaterialTheme.typography.displaySmall
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Artist: ${track.artist}",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}