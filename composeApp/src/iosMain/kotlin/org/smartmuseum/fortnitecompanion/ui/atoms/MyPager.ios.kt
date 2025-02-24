package org.smartmuseum.fortnitecompanion.ui.atoms

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

// iosMain
actual class MyPager {
    @Composable
    actual fun Pager(count: Int, content: @Composable (Int) -> Unit) {
        var pageWidthPx by remember { mutableIntStateOf(0) }
        val listState = rememberLazyListState()
        var currentPage by remember { mutableIntStateOf(0) }
        val density = LocalDensity.current
        val currentPageOffset by remember {
            derivedStateOf {
                val layoutInfo = listState.layoutInfo
                val visibleItemsInfo = layoutInfo.visibleItemsInfo
                if (layoutInfo.totalItemsCount == 0) {
                    0f
                } else {
                    val currentItemInfo = visibleItemsInfo.firstOrNull { it.index == currentPage }
                        ?: return@derivedStateOf 0f
                    val itemOffset = currentItemInfo.offset
                    val itemSize = currentItemInfo.size
                    (itemOffset.toFloat() / itemSize.toFloat())
                }
            }
        }

        LaunchedEffect(key1 = currentPageOffset) {
            val targetPage = if (currentPageOffset.absoluteValue > 0.5) {
                if (currentPageOffset < 0) {
                    (currentPage - 1).coerceAtLeast(0)
                } else {
                    (currentPage + 1).coerceAtMost(count - 1)
                }
            } else {
                currentPage
            }
            if (targetPage != currentPage) {
                currentPage = targetPage
            }
        }

        LaunchedEffect(key1 = currentPage) {
            listState.animateScrollToItem(currentPage)
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyRow(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .draggable(
                        orientation = Orientation.Horizontal,
                        state = rememberDraggableState { delta ->
                            val targetOffset = (delta / pageWidthPx)
                            val targetPage = (currentPageOffset + targetOffset)
                            val targetPageInt = targetPage.roundToInt()
                            if (targetPageInt != 0) {
                                currentPage = (currentPage - targetPageInt).coerceIn(0, count - 1)
                            }
                        }
                    )
                    .onSizeChanged {
                        pageWidthPx = it.width
                    }
            ) {
                items(count) { page ->
                    Box(modifier = Modifier.width(with(density) { pageWidthPx.toDp() })) {
                        content(page)
                    }
                }
            }
        }
    }
}