package org.smartmuseum.fortnitecompanion.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import chaintech.videoplayer.ui.preview.VideoPreviewComposable
import coil3.compose.AsyncImage
import org.smartmuseum.fortnitecompanion.data.cosmetics.Cosmetic
import org.smartmuseum.fortnitecompanion.data.generic.Images

@Composable
fun FortniteCosmetic(fortniteCosmetic: Cosmetic, useSmallIcon: Boolean = false) {

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
            Text(modifier = Modifier.fillMaxWidth(0.3f), text = "Name")
            Text(modifier = Modifier.fillMaxWidth(0.7f), text = fortniteCosmetic.getNameOrId())
        }
        fortniteCosmetic.description?.let {
            Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                Text(modifier = Modifier.fillMaxWidth(0.3f), text = "Description")
                Text(modifier = Modifier.fillMaxWidth(0.7f), text = it)
            }
        }
        fortniteCosmetic.type?.let {
            Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                Text(modifier = Modifier.fillMaxWidth(0.3f), text = "Type")
                Text(modifier = Modifier.fillMaxWidth(0.7f), text = it.displayValue)
            }
        }
        fortniteCosmetic.rarity?.let {
            Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                Text(modifier = Modifier.fillMaxWidth(0.3f), text = "Rarity")
                Text(modifier = Modifier.fillMaxWidth(0.7f), text = it.displayValue)
            }
        }
        fortniteCosmetic.introduction?.let {
            Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                Text(modifier = Modifier.fillMaxWidth(0.3f), text = "Introduction")
                Text(modifier = Modifier.fillMaxWidth(0.7f), text = it.text)
            }
        }
        fortniteCosmetic.images?.let { images: Images ->
            if (useSmallIcon) {
                images.smallIcon?.let { icon ->
                    AsyncImage(modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 50.dp), model = icon, contentDescription = null)
                }
            }
            else {
                images.icon?.let { icon ->
                    AsyncImage(modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 50.dp), model = icon, contentDescription = null)
                }
            }
        }
        fortniteCosmetic.showcaseVideo?.let { showcaseVideo ->
            VideoPreviewComposable(url = showcaseVideo, frameCount = 5)
        }
        Divider(modifier = Modifier.fillMaxWidth(0.9f), thickness = 1.dp)
    }
}
