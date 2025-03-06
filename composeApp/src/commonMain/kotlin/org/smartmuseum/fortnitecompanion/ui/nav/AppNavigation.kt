package org.smartmuseum.fortnitecompanion.ui.nav

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.compose.rememberNavController
import com.diamondedge.logging.KmLog
import fortnitecompanionapp.composeapp.generated.resources.Res
import fortnitecompanionapp.composeapp.generated.resources.account_found
import fortnitecompanionapp.composeapp.generated.resources.account_not_found
import fortnitecompanionapp.composeapp.generated.resources.account_private
import fortnitecompanionapp.composeapp.generated.resources.app_name
import fortnitecompanionapp.composeapp.generated.resources.details
import fortnitecompanionapp.composeapp.generated.resources.loading
import fortnitecompanionapp.composeapp.generated.resources.map
import fortnitecompanionapp.composeapp.generated.resources.no_internet_connection
import fortnitecompanionapp.composeapp.generated.resources.search
import fortnitecompanionapp.composeapp.generated.resources.shop
import org.jetbrains.compose.resources.StringResource
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import org.smartmuseum.fortnitecompanion.storage.KMMSharedPrefs

enum class Screen {
    ACCOUNT_FOUND,
    ACCOUNT_NOT_FOUND,
    ACCOUNT_PRIVATE,
    FULL_COSMETIC,
    FIND_PLAYER_STATS,
    LOADING,
    MAP,
    BATTLE_ROYALE_COSMETICS,
    BANNERS,
    NO_INTERNET_CONNECTION,
    SHOP,
    FAILED_SHOP
}

enum class NavigationGraphs(val graph: String) {
    MapGraph("map_graph"),
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
    data object Map : NavigationItem(Screen.MAP.name)
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
            NavigationItem.FullCosmetic.route -> Res.string.details
            NavigationItem.FindPlayerStats.route -> Res.string.search
            NavigationItem.AccountFound.route -> Res.string.account_found
            NavigationItem.AccountNotFound.route -> Res.string.account_not_found
            NavigationItem.AccountPrivate.route -> Res.string.account_private
            NavigationItem.Loading.route -> Res.string.loading
            NavigationItem.Shop.route -> Res.string.shop
            NavigationItem.Map.route -> Res.string.map
            NavigationItem.NoInternetConnection.route -> Res.string.no_internet_connection
            else -> Res.string.app_name
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

// New function to get the graph name
fun getGraphName(destination: NavDestination): String? {
    var currentDestination = destination
    while (currentDestination.parent != null) {
        currentDestination = currentDestination.parent!!
    }
    return currentDestination.route
}
