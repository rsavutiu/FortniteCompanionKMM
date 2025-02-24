package org.smartmuseum.fortnitecompanion.ui.nav

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
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
    FULL_COSMETIC,
    SEARCH_TEXT_FIELD,
    LOADING,
    BATTLE_ROYALE_COSMETICS,
    NO_INTERNET_CONNECTION
}

enum class NavigationGraphs(val route: String) {
    CosmeticsGraph("cosmetics_graph"),
    PlayerStatsGraph("player_stats_graph")
}

sealed class NavigationItem(val route: String) {
    data object FullCosmetic : NavigationItem(Screen.FULL_COSMETIC.name)
    data object SearchTextField : NavigationItem(Screen.SEARCH_TEXT_FIELD.name)
    data object BattleRoyaleCosmetics : NavigationItem(Screen.BATTLE_ROYALE_COSMETICS.name)
    data object Loading : NavigationItem(Screen.LOADING.name)
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
    val currentDestination: MutableState<String?> =
        mutableStateOf(navController.currentDestination?.route)
    val appBarTitle: State<StringResource> = derivedStateOf {
        when (currentDestination.value) {
            NavigationItem.FullCosmetic.route -> resources.strings.details
            NavigationItem.SearchTextField.route -> resources.strings.search
            NavigationItem.Loading.route -> resources.strings.loading
            NavigationItem.NoInternetConnection.route -> resources.strings.no_internet_connection
            else -> resources.strings.app_name
        }
    }
    navController.addOnDestinationChangedListener { _, destination, _ ->
        log.i { "currentDestination ${destination.route}" }
        currentDestination.value = destination.route
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
            startDestination = NavigationGraphs.CosmeticsGraph.route,
            drawerState = drawerState
        )
    }
}

@Composable
fun drawerContent(
    navController: NavController,
    drawerState: DrawerState,
    coroutineScope: CoroutineScope,
    currentDestination: MutableState<String?>
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
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(resources.strings.search),
                    )
                },
                label = { Text(text = stringResource(resources.strings.search)) },
                selected = currentDestination.value == NavigationItem.SearchTextField.route,
                onClick = {
                    navController.navigate(route = NavigationGraphs.PlayerStatsGraph.route)
                    navController.navigate(NavigationItem.SearchTextField.route)
                    coroutineScope.launch {
                        drawerState.close()
                    }
                }
            )
            NavigationDrawerItem(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = stringResource(resources.strings.battle_royale_cosmetics),
                    )
                },
                label = { Text(text = stringResource(resources.strings.battle_royale_cosmetics)) },
                selected = currentDestination.value == NavigationItem.BattleRoyaleCosmetics.route,
                onClick = {
                    navController.navigate(route = NavigationGraphs.CosmeticsGraph.route)
                    navController.navigate(NavigationItem.BattleRoyaleCosmetics.route)
                    coroutineScope.launch {
                        drawerState.close()
                    }
                }
            )
        }
    }
}
