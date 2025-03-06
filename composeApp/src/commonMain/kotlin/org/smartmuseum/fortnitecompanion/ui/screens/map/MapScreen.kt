package org.smartmuseum.fortnitecompanion.ui.screens.map

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.IntSize
import coil3.compose.AsyncImage
import com.diamondedge.logging.KmLog
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import org.smartmuseum.fortnitecompanion.data.map.MapResponse
import org.smartmuseum.fortnitecompanion.utils.drawPoi
import kotlin.math.max

@Composable
fun MapScreen(mapResponse: MapResponse) {
    val worldRadius = 135000
    val mapImageUrl = mapResponse.data.images.blank
    val pois = mapResponse.data.pois
    val textMeasurer = rememberTextMeasurer()
    val textColor = MaterialTheme.colorScheme.onPrimary
    val fontSize = MaterialTheme.typography.bodyLarge.fontSize
    val kmLog = koinInject<KmLog> { parametersOf("MapScreen") }
    kmLog.i { "Map screen: $mapResponse" }

    var imageSize by remember { mutableStateOf(IntSize.Zero) }
    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth(2f) // Make the image wider than the screen
                .fillMaxSize() // Remove fixed height and fill the height
                .onSizeChanged { imageSize = it }
                .horizontalScroll(scrollState), // Add horizontal scrolling
            model = mapImageUrl,
            contentDescription = null,
            contentScale = ContentScale.Fit // Use Fit to avoid cropping
        )
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasWidth = size.width * 1.7f
            val imageHeight = imageSize.height.toFloat()
            val scrollPosition = scrollState.value

            pois?.filter { it.location != null }?.forEach { poi ->
                // Calculate the scaled POI position
                // Use canvasWidth here instead of imageWidth
                val mx = max(0f, (poi.location!!.x + worldRadius) / (worldRadius * 2) * canvasWidth)
                // Invert the Y-axis calculation here:
                val my = max(0f, (poi.location.y + worldRadius) / (worldRadius * 2) * imageHeight)

                // Adjust the POI position based on the image's scroll position
                val adjustedMx = mx - scrollPosition

                drawPoi(
                    textColor = textColor,
                    x = adjustedMx,
                    y = my,
                    name = poi.name ?: poi.id,
                    textMeasurer = textMeasurer,
                    fontSize = fontSize,
                    imageHeight = imageHeight
                )
            }
        }
    }
}