package org.smartmuseum.fortnitecompanion.ui.molecules

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.ImageResource
import kotlinx.coroutines.delay

@Composable
fun ImageCarousel(
    modifier: Modifier = Modifier,
    images: List<ImageResource>,
    autoScrollDuration: Long = 3000L
) {
    val pagerState = rememberPagerState(pageCount = { images.size })

    LaunchedEffect(key1 = pagerState.currentPage) {
        delay(autoScrollDuration)
        val nextPage = (pagerState.currentPage + 1) % images.size
        pagerState.animateScrollToPage(nextPage)
    }

    Box(modifier = modifier) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            val imageItem: ImageResource = images[page]
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = dev.icerock.moko.resources.compose.painterResource(imageItem),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Text(
            text = "${pagerState.currentPage + 1}/${images.size}",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )
    }
}