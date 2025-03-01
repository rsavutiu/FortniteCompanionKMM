package org.smartmuseum.fortnitecompanion.ui.screens.cosmetics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.smartmuseum.fortnitecompanion.data.cosmetics.CosmeticType
import org.smartmuseum.fortnitecompanion.data.cosmetics.ICosmetic
import org.smartmuseum.fortnitecompanion.resources
import org.smartmuseum.fortnitecompanion.ui.CosmeticItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CosmeticsList(
    modifier: Modifier = Modifier,
    navController: NavController,
    cosmeticsMap: Map<String?, List<ICosmetic>>,
    onSelectCosmetic: (ICosmetic) -> Unit = {}
) {
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { cosmeticsMap.size })
    val tabs = cosmeticsMap.keys.toList()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (tabs.size > 1) {
            ScrollableTabRow(selectedTabIndex = pagerState.currentPage) {
                tabs.forEachIndexed { index, it ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                        text = {
                            Text(
                                it ?: stringResource(resources.strings.unknown_tab),
                                fontSize = MaterialTheme.typography.titleLarge.fontSize
                            )
                        },
                        selectedContentColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedContentColor = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            val cosmetics: List<ICosmetic> = cosmeticsMap[tabs[page]]!!
            val cosmeticsByType: Map<CosmeticType?, List<ICosmetic>> =
                cosmetics.groupBy { it.getCosmeticType() }
            val cosmeticTypes: List<CosmeticType?> = cosmeticsByType.keys.toList()
            val innerPagerState = rememberPagerState(pageCount = { cosmeticTypes.size })
            Column {
                if (cosmeticTypes.size > 1) {
                    ScrollableTabRow(selectedTabIndex = innerPagerState.currentPage) {
                        cosmeticTypes.forEachIndexed { index, it ->
                            Tab(
                                selected = innerPagerState.currentPage == index,
                                onClick = {
                                    coroutineScope.launch {
                                        innerPagerState.animateScrollToPage(
                                            index
                                        )
                                    }
                                },
                                text = {
                                    Text(
                                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                                        text = it?.displayValue ?: it?.value
                                        ?: stringResource(resources.strings.unknown_tab),
                                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                                    )
                                },
                                icon = {
                                    it?.getCosmeticTypeImage()?.let { type: ImageResource ->
                                        Column(modifier = Modifier.height(24.dp)) {
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Icon(
                                                painter = painterResource(type),
                                                tint = null,
                                                contentDescription = null,
                                                modifier = Modifier.size(16.dp).padding(8.dp)
                                            )
                                        }
                                    }
                                },
                                selectedContentColor = MaterialTheme.colorScheme.onPrimary,
                                unselectedContentColor = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
                HorizontalPager(
                    modifier = Modifier.fillMaxSize(),
                    state = innerPagerState
                ) { innerPage ->
                    LazyColumn(
                        modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        cosmeticsByType[cosmeticTypes[innerPage]]!!.forEach { cosmetic ->
                            item {
                                CosmeticItem(
                                    navController = navController,
                                    fortniteCosmetic = cosmetic,
                                    onSelectCosmetic = onSelectCosmetic
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}