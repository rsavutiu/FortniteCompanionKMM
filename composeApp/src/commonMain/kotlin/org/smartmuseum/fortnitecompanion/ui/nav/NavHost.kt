package org.smartmuseum.fortnitecompanion.ui.nav

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.plusmobileapps.konnectivity.Konnectivity
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import org.lighthousegames.logging.KmLog
import org.smartmuseum.fortnitecompanion.data.cosmetics.BannerResponse
import org.smartmuseum.fortnitecompanion.data.cosmetics.CosmeticEnum
import org.smartmuseum.fortnitecompanion.data.cosmetics.CosmeticsUiData
import org.smartmuseum.fortnitecompanion.data.cosmetics.ICosmetic
import org.smartmuseum.fortnitecompanion.data.map.MapResponse
import org.smartmuseum.fortnitecompanion.data.shop.ShopResponse
import org.smartmuseum.fortnitecompanion.networking.NetworkResult
import org.smartmuseum.fortnitecompanion.resources
import org.smartmuseum.fortnitecompanion.ui.screens.banners.BannersScreen
import org.smartmuseum.fortnitecompanion.ui.screens.cosmetics.CosmeticsTabs
import org.smartmuseum.fortnitecompanion.ui.screens.cosmetics.FullCosmeticScreen
import org.smartmuseum.fortnitecompanion.ui.screens.cosmetics.LoadingScreen
import org.smartmuseum.fortnitecompanion.ui.screens.cosmetics.PlayerStatsScreen
import org.smartmuseum.fortnitecompanion.ui.screens.generic.ErrorScreen
import org.smartmuseum.fortnitecompanion.ui.screens.generic.NoInternetScreen
import org.smartmuseum.fortnitecompanion.ui.screens.map.MapScreen
import org.smartmuseum.fortnitecompanion.ui.screens.shop.ShopScreen
import org.smartmuseum.fortnitecompanion.ui.screens.stats.AccountNotFound
import org.smartmuseum.fortnitecompanion.ui.screens.stats.FindPlayerStatsScreen
import org.smartmuseum.fortnitecompanion.usecases.FindStatsResult
import org.smartmuseum.fortnitecompanion.viewmodel.CosmeticsViewModel
import org.smartmuseum.fortnitecompanion.viewmodel.FindPlayerStatsViewModel
import org.smartmuseum.fortnitecompanion.viewmodel.MapViewModel
import org.smartmuseum.fortnitecompanion.viewmodel.ShopViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavHost(
    modifier: Modifier,
    navController: NavHostController,
    layoutConfigIsPortrait: Boolean,
    coroutineScope: CoroutineScope,
    startDestination: String,
    drawerState: DrawerState,
    appTitle: State<StringResource>,
) {
    val log: KmLog = koinInject<KmLog> { parametersOf("AppNavHost") }
    val connectivity = koinInject<Konnectivity>()
    val isConnected: State<Boolean> = connectivity.isConnectedState.collectAsState()
    val shopViewModel: ShopViewModel = viewModel()
    val cosmeticsViewModel: CosmeticsViewModel = viewModel()
    val mapViewModel: MapViewModel = viewModel()
    val findPlayerStatsViewModel: FindPlayerStatsViewModel = viewModel()
    val shopValue: NetworkResult<ShopResponse> = shopViewModel.shopResult.collectAsState().value
    val mapValue: NetworkResult<MapResponse> = mapViewModel.mapResult.collectAsState().value
    val bannerResponse: NetworkResult<BannerResponse> =
        cosmeticsViewModel.bannerNetworkStatus.collectAsState().value
    coroutineScope.launch {
        findPlayerStatsViewModel.statsResult.onEach {
            log.i { "statsResultState received $it" }
            when (it) {
                is FindStatsResult.AccountNotfound -> {
                    navigateManuallyToScreenAndLog(
                        log = log,
                        navController = navController,
                        graph = NavigationGraphs.PlayerStatsGraph.graph,
                        route = NavigationItem.AccountNotFound.route,
                    )
                }

                is FindStatsResult.AccountPrivate -> navigateManuallyToScreenAndLog(
                    log = log,
                    navController = navController,
                    graph = null,
                    route = NavigationItem.NoInternetConnection.route,
                )

                is FindStatsResult.Loading -> {
                    navigateManuallyToScreenAndLog(
                        log = log,
                        navController = navController,
                        graph = null,
                        route = NavigationItem.Loading.route,
                    )
                }

                is FindStatsResult.Success ->
                    navigateManuallyToScreenAndLog(
                        log = log,
                        navController = navController,
                        graph = NavigationGraphs.PlayerStatsGraph.graph,
                        route = NavigationItem.AccountFound.route,
                    )

                is FindStatsResult.UnknownError -> navigateManuallyToScreenAndLog(
                    log = log,
                    navController = navController,
                    graph = null,
                    route = NavigationItem.NoInternetConnection.route,
                )

                null -> {}
            }
        }.collect {}
    }
    LaunchedEffect(appTitle.value) {
        log.i { "appBarTitle.value ${appTitle.value}" }
    }
    Column {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
            ),
            title = {
                Text(stringResource(appTitle.value))
            },
            actions = {

            },
            navigationIcon = {
                IconButton(onClick = {
                    if (drawerState.isOpen) {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                    } else {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = stringResource(resources.strings.menu),
                        modifier = Modifier.padding(8.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        )
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination
        ) {
            navigation(
                startDestination = NavigationItem.Shop.route,
                route = NavigationGraphs.ShopGraph.graph
            ) {
                log.i { "Composable shop recompose $shopValue" }
                composable(route = NavigationItem.FailedShop.route) { NoInternetScreen() }
                composable(route = NavigationItem.Loading.route) { LoadingScreen() }
                composable(route = NavigationItem.Shop.route) {
                    if (shopValue is NetworkResult.Success) {
                        log.i { "Composable shop recompose2 $shopValue" }
                        ShopScreen(
                            shopResponse = shopValue.data,
                        )
                    } else {
                        navController.navigate(NavigationItem.FailedShop.route)
                    }
                }
            }
            navigation(
                startDestination = NavigationItem.Map.route,
                route = NavigationGraphs.MapGraph.graph
            ) {
                composable(route = NavigationItem.Map.route)
                {
                    mapViewModel.load()
                    when (mapValue) {
                        is NetworkResult.Error -> ErrorScreen { mapViewModel.load() }
                        NetworkResult.Loading -> LoadingScreen()
                        NetworkResult.Ready -> {
                            NoInternetScreen()
                            mapViewModel.load()
                        }

                        is NetworkResult.Success -> MapScreen(mapResponse = mapValue.data)
                    }
                }
            }
            navigation(
                startDestination = NavigationItem.BattleRoyaleCosmetics.route,
                route = NavigationGraphs.CosmeticsGraph.graph
            ) {
                composable(route = NavigationItem.Banners.route) {
                    cosmeticsViewModel.getBanners()
                    when (bannerResponse) {
                        is NetworkResult.Error -> ErrorScreen(onRetry = {
                            cosmeticsViewModel.getBanners()
                        })

                        NetworkResult.Ready, NetworkResult.Loading -> LoadingScreen()
                        is NetworkResult.Success -> {
                            BannersScreen(
                                navController = navController,
                                bannerResponse = bannerResponse.data,
                                onSelectCosmetic = { newFullCosmetic ->
                                    coroutineScope.launch {
                                        cosmeticsViewModel.setFullCosmetic(
                                            newFullCosmetic
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
                composable(route = NavigationItem.BattleRoyaleCosmetics.route) {
                    CosmeticsTabs(
                        navController = navController,
                        drawerState = drawerState,
                        coroutineScope = coroutineScope,
                        cosmeticsList = CosmeticEnum.entries.map {
                            CosmeticsUiData(
                                title = stringResource(it.stringResource),
                                cosmeticsEnum = it,
                                refresh = cosmeticsViewModel.refresh[it]!!,
                                responseState = cosmeticsViewModel.cosmeticNetworkStatusMap[it]!!.collectAsState()
                            )
                        },
                        onSelectCosmetic = { newFullCosmetic ->
                            coroutineScope.launch {
                                cosmeticsViewModel.setFullCosmetic(
                                    newFullCosmetic
                                )
                            }
                        }
                    )
                }
                composable(route = NavigationItem.FullCosmetic.route) {
                    val fullDetail: State<ICosmetic?> =
                        cosmeticsViewModel.fullDetailCosmetic.collectAsState()
                    FullCosmeticScreen(fullDetail)
                }
            }
            navigation(
                startDestination = NavigationItem.FindPlayerStats.route,
                route = NavigationGraphs.PlayerStatsGraph.graph
            ) {
                composable(route = NavigationItem.FindPlayerStats.route) {
                    FindPlayerStatsScreen(
                        onSearchClicked = { query ->
                            findPlayerStatsViewModel.setPlayerQuery(query)
                        }
                    )
                }
                composable(route = NavigationItem.AccountFound.route) {
                    if (findPlayerStatsViewModel.statsResult.value is FindStatsResult.Success) {
                        PlayerStatsScreen(
                            coroutineScope = coroutineScope,
                            stats = (findPlayerStatsViewModel.statsResult.value as FindStatsResult.Success).stats.data
                        )
                    }
                }
                composable(route = NavigationItem.AccountNotFound.route) {
                    AccountNotFound()
                }
            }
            composable(route = NavigationItem.NoInternetConnection.route) {
                NoInternetScreen()
            }
            composable(route = NavigationItem.Loading.route) {
                LoadingScreen()
            }
        }
    }

    LaunchedEffect(isConnected.value) {
        if (!isConnected.value) {
            navController.navigate(NavigationItem.NoInternetConnection.route)
        } else {
            navController.navigate(startDestination)
        }
    }
}

private fun navigateManuallyToScreenAndLog(
    log: KmLog,
    navController: NavHostController,
    graph: String?,
    route: String,
) {
    log.i(
        tag = "AppNavigation",
        msg = { " Navigate to $graph / $route" })
    graph?.let {
        navController.navigate(it)
    }
    navController.navigate(route) {
        navController.graph.route?.let { graphRoute ->
            popUpTo(graphRoute) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}
