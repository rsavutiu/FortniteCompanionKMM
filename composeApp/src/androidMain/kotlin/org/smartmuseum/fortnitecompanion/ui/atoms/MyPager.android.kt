package org.smartmuseum.fortnitecompanion.ui.atoms

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable

// commonMain
actual class MyPager {
    @Composable
    actual fun Pager(count: Int, content: @Composable (Int) -> Unit) {
        val pagerState = rememberPagerState(pageCount = { count })
        HorizontalPager(state = pagerState) { page ->
            content(page)
        }
    }
}