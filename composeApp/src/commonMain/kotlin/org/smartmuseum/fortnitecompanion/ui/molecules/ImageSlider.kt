package org.smartmuseum.fortnitecompanion.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import org.smartmuseum.fortnitecompanion.ui.atoms.HorizontalPagerIndicator

@Composable
fun ImageSlider(modifier: Modifier = Modifier, listOfUrls: List<String>) {
    if (listOfUrls.isEmpty()) return

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        listOfUrls.size
    }

    Column(modifier = modifier) {
        HorizontalPager(state = pagerState) { page ->
            Image(
                painter = rememberAsyncImagePainter(model = listOfUrls[page]),
                contentDescription = "Image Slider",
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.98f),
                contentScale = ContentScale.Crop
            )
        }
        if (listOfUrls.size > 1) {
            Spacer(modifier = Modifier.height(4.dp))
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

