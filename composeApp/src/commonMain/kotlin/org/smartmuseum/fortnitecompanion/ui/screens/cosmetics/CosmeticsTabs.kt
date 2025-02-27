package org.smartmuseum.fortnitecompanion.ui.screens.cosmetics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.smartmuseum.fortnitecompanion.data.cosmetics.CosmeticsUiData
import org.smartmuseum.fortnitecompanion.data.cosmetics.ICosmetic
import org.smartmuseum.fortnitecompanion.networking.NetworkResult

@Composable
fun CosmeticsTabs(
    navController: NavController,
    drawerState: DrawerState,
    coroutineScope: CoroutineScope,
    cosmeticsList: List<CosmeticsUiData>,
    onSelectCosmetic: (ICosmetic) -> Unit
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState(pageCount = { cosmeticsList.size })
    val minTabWidth = 100.dp
    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }
    for (callback in cosmeticsList) {
        LaunchedEffect(true) {
            if (callback.responseState.value is NetworkResult.Error ||
                callback.responseState.value is NetworkResult.Ready
            )
                callback.refresh()
        }
    }
    Column {
        ScrollableTabRow(selectedTabIndex = pagerState.currentPage) {
            cosmeticsList.forEachIndexed { index, cosmeticCallback ->
                Tab(
                    modifier = Modifier.widthIn(min = minTabWidth),
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch { pagerState.animateScrollToPage(index) }
                    },
                    text = {
                        Text(
                            cosmeticCallback.title,
                            fontSize = MaterialTheme.typography.headlineSmall.fontSize
                        )
                    },
                    selectedContentColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedContentColor = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            CosmeticListScreen(
                navController = navController,
                cosmeticEnum = cosmeticsList[page].cosmeticsEnum,
                refresh = cosmeticsList[page].refresh,
                state = cosmeticsList[page].responseState,
                onSelectCosmetic = onSelectCosmetic
            )
        }
    }
}