package org.smartmuseum.fortnitecompanion.ui.nav

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.rememberNavController
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import org.lighthousegames.logging.KmLog
import org.smartmuseum.fortnitecompanion.resources
import org.smartmuseum.fortnitecompanion.storage.KMMSharedPrefs

enum class Screen {
    ACCOUNT_FOUND,
    ACCOUNT_NOT_FOUND,
    ACCOUNT_PRIVATE,
    FULL_COSMETIC,
    FIND_PLAYER_STATS,
    LOADING,
    BATTLE_ROYALE_COSMETICS,
    BANNERS,
    NO_INTERNET_CONNECTION,
    SHOP,
    FAILED_SHOP
}

enum class NavigationGraphs(val graph: String) {
    CosmeticsGraph("cosmetics_graph"),
    PlayerStatsGraph("player_stats_graph"),
    ShopGraph("shop_graph")
}

sealed class NavigationItem(val route: String) {
    data object Shop : NavigationItem(Screen.SHOP.name)
    data object FullCosmetic : NavigationItem(Screen.FULL_COSMETIC.name)
    data object FindPlayerStats : NavigationItem(Screen.FIND_PLAYER_STATS.name)
    data object AccountFound : NavigationItem(Screen.ACCOUNT_FOUND.name)
    data object AccountNotFound : NavigationItem(Screen.ACCOUNT_NOT_FOUND.name)
    data object AccountPrivate : NavigationItem(Screen.ACCOUNT_PRIVATE.name)
    data object BattleRoyaleCosmetics : NavigationItem(Screen.BATTLE_ROYALE_COSMETICS.name)
    data object Banners : NavigationItem(Screen.BANNERS.name)
    data object Loading : NavigationItem(Screen.LOADING.name)
    data object FailedShop : NavigationItem(Screen.FAILED_SHOP.name)
    data object NoInternetConnection : NavigationItem(Screen.NO_INTERNET_CONNECTION.name)
}

@Composable
fun AppNavHost(
    kmmSharedPrefs: KMMSharedPrefs,
    modifier: Modifier = Modifier,
    layoutConfigIsPortrait: State<Boolean>,
) {
    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val log: KmLog = koinInject<KmLog> { parametersOf("AppNavHost") }
    val currentDestination: MutableState<Pair<String?, String?>> =
        mutableStateOf(
            Pair(
                navController.currentDestination?.route
                    ?: NavigationItem.BattleRoyaleCosmetics.route, null
            )
        )

    val appBarTitle: State<StringResource> = derivedStateOf {
        log.i { "appBarTitle ${currentDestination.value.first}" }
        when (currentDestination.value.first) {
            NavigationItem.FullCosmetic.route -> resources.strings.details
            NavigationItem.FindPlayerStats.route -> resources.strings.search
            NavigationItem.AccountFound.route -> resources.strings.account_found
            NavigationItem.AccountNotFound.route -> resources.strings.account_not_found
            NavigationItem.AccountPrivate.route -> resources.strings.account_private
            NavigationItem.Loading.route -> resources.strings.loading
            NavigationItem.Shop.route -> resources.strings.shop
            NavigationItem.NoInternetConnection.route -> resources.strings.no_internet_connection
            else -> resources.strings.app_name
        }

    }
    navController.addOnDestinationChangedListener { _, destination, _ ->
        log.i { "currentDestination ${destination.route}" }
        currentDestination.value = Pair(destination.route, getGraphName(destination))
    }
    LaunchedEffect(drawerState.currentValue) {
        log.i { "drawerState ${drawerState.currentValue}" }
    }
    log.i { "Compose" }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            drawerContent(
                navController = navController,
                drawerState = drawerState,
                currentDestination = currentDestination,
                coroutineScope = coroutineScope
            )
        }) {
        NavHost(
            modifier = modifier,
            navController = navController,
            layoutConfigIsPortrait = layoutConfigIsPortrait.value,
            coroutineScope = coroutineScope,
            startDestination = NavigationGraphs.CosmeticsGraph.graph,
            drawerState = drawerState,
            appTitle = appBarTitle
        )
    }
}

@Composable
fun drawerContent(
    navController: NavController,
    drawerState: DrawerState,
    coroutineScope: CoroutineScope,
    currentDestination: MutableState<Pair<String?, String?>>
) {
    ModalDrawerSheet(
        drawerState = drawerState,
        drawerContentColor = MaterialTheme.colorScheme.onSurface,
        drawerContainerColor = MaterialTheme.colorScheme.surface
    ) {
        Column {
            NavBarHeader()
            NavigationDrawerItem(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = stringResource(resources.strings.shop),
                    )
                },
                label = { Text(text = stringResource(resources.strings.shop)) },
                selected = currentDestination.value.second == NavigationGraphs.ShopGraph.graph,
                onClick = {
                    navController.navigate(route = NavigationGraphs.ShopGraph.graph)
                    navController.navigate(NavigationItem.Shop.route)
                    coroutineScope.launch {
                        drawerState.close()
                    }
                }
            )
            NavigationDrawerItem(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(resources.strings.search),
                    )
                },
                label = { Text(text = stringResource(resources.strings.search)) },
                selected = currentDestination.value.second == NavigationGraphs.PlayerStatsGraph.graph,
                onClick = {
                    navController.navigate(route = NavigationGraphs.PlayerStatsGraph.graph)
                    navController.navigate(NavigationItem.FindPlayerStats.route)
                    coroutineScope.launch {
                        drawerState.close()
                    }
                }
            )
            NavigationDrawerItem(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = stringResource(resources.strings.cosmetics),
                    )
                },
                label = { Text(text = stringResource(resources.strings.battle_royale_cosmetics)) },
                selected = currentDestination.value.second == NavigationGraphs.PlayerStatsGraph.graph,
                onClick = {
                    navController.navigate(route = NavigationGraphs.CosmeticsGraph.graph)
                    navController.navigate(NavigationItem.BattleRoyaleCosmetics.route)
                    coroutineScope.launch {
                        drawerState.close()
                    }
                }
            )
            NavigationDrawerItem(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Warning,
                        contentDescription = stringResource(resources.strings.banners),
                    )
                },
                label = { Text(text = stringResource(resources.strings.banners)) },
                selected = currentDestination.value.second == NavigationGraphs.CosmeticsGraph.graph,
                onClick = {
                    navController.navigate(route = NavigationGraphs.CosmeticsGraph.graph)
                    navController.navigate(NavigationItem.Banners.route)
                    coroutineScope.launch {
                        drawerState.close()
                    }
                }
            )
        }
    }
}

// New function to get the graph name
fun getGraphName(destination: NavDestination): String? {
    var currentDestination = destination
    while (currentDestination.parent != null) {
        currentDestination = currentDestination.parent!!
    }
    return currentDestination.route
}
