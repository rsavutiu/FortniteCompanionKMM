package org.smartmuseum.fortnitecompanion.ui.screens.cosmetics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.smartmuseum.fortnitecompanion.data.stats.BattlePass
import org.smartmuseum.fortnitecompanion.data.stats.PlayerAccount
import org.smartmuseum.fortnitecompanion.data.stats.PlayerStatsData
import org.smartmuseum.fortnitecompanion.data.stats.StatsCategory
import org.smartmuseum.fortnitecompanion.data.stats.StatsMode
import org.smartmuseum.fortnitecompanion.resources
import org.smartmuseum.fortnitecompanion.ui.molecules.SimpleFloatGraph

@Composable
fun PlayerStatsScreen(
    coroutineScope: CoroutineScope,
    stats: PlayerStatsData
) {
    val cardColors = CardColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        disabledContainerColor = MaterialTheme.colorScheme.inverseSurface,
        disabledContentColor = MaterialTheme.colorScheme.inverseOnSurface
    )
    val tabs = stats.getTabs()
    val pagerState = rememberPagerState(pageCount = { tabs.size + 1 })
    Column {
        ScrollableTabRow(selectedTabIndex = pagerState.currentPage) {
            Tab(
                selected = pagerState.currentPage == 0,
                onClick = { coroutineScope.launch { pagerState.animateScrollToPage(0) } },
                text = { Text(stringResource(resources.strings.account_found)) },
                selectedContentColor = MaterialTheme.colorScheme.onPrimary,
                unselectedContentColor = MaterialTheme.colorScheme.onSurface
            )
            tabs.forEachIndexed { index, tab ->
                Tab(
                    text = { Text(tab.first) },
                    selected = pagerState.currentPage == index + 1,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index + 1) } },
                    selectedContentColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedContentColor = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            AccountInfoCard(account = stats.account, cardColors = cardColors)
                        }
                        item {
                            BattlePassInfoCard(
                                battlePass = stats.battlePass,
                                cardColors = cardColors
                            )
                        }
                    }
                }

                else -> {
                    val tab = tabs[page - 1]
                    StatsSection(stats = tab.second, tab.first, cardColors = cardColors)
                }
            }
        }
    }
}

@Composable
fun AccountInfoCard(account: PlayerAccount, cardColors: CardColors) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = cardColors
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Account Information",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "ID: ${account.id}")
            Text(text = "Name: ${account.name}")
        }
    }
}

@Composable
fun BattlePassInfoCard(battlePass: BattlePass, cardColors: CardColors) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = cardColors
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Battle Pass",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Level: ${battlePass.level}")
            Text(text = "Progress: ${battlePass.progress}%")
        }
    }
}

@Composable
fun StatsSection(stats: StatsCategory, title: String, cardColors: CardColors) {
    val coroutineScope = rememberCoroutineScope()
    val statsMap: LinkedHashMap<String, StatsMode> = stats.getNonnullStatsMap()
    val pagerState = rememberPagerState(pageCount = { statsMap.size })
    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        ScrollableTabRow(selectedTabIndex = pagerState.currentPage) {
            statsMap.onEachIndexed { index, it ->
                Tab(
                    selected = pagerState.currentPage == 0,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                    text = { Text(it.key) },
                    selectedContentColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedContentColor = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        HorizontalPager(state = pagerState) {
            statsMap.onEachIndexed { index: Int, entry: Map.Entry<String, StatsMode> ->
                LazyColumn {
                    item {
                        Text(
                            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                            text = entry.key,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    entry.value.toMap().forEach { (key, value) ->
                        item {
                            StatsRow(labelResource = key, value = value)
                        }
                    }
                    item {
                        SimpleFloatGraph(dataPoints = entry.value.getDataPoints())
                    }
                }
            }
        }
    }
}

@Composable
fun StatsRow(labelResource: StringResource, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(resource = labelResource),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(text = value, style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.End)
    }
}

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Loading...")
    }
}

@Composable
fun AccountNotFoundScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Account not found")
    }
}

@Composable
fun AccountPrivateScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Account is private")
    }
}

@Composable
fun UnknownErrorScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Unknown error")
    }
}

@Composable
fun EmptyScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Empty")
    }
}