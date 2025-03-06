package org.smartmuseum.fortnitecompanion.ui.nav

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import fortnitecompanionapp.composeapp.generated.resources.Res
import fortnitecompanionapp.composeapp.generated.resources.banners
import fortnitecompanionapp.composeapp.generated.resources.battle_royale_cosmetics
import fortnitecompanionapp.composeapp.generated.resources.cosmetics
import fortnitecompanionapp.composeapp.generated.resources.flag_icon
import fortnitecompanionapp.composeapp.generated.resources.map
import fortnitecompanionapp.composeapp.generated.resources.map_icon
import fortnitecompanionapp.composeapp.generated.resources.search
import fortnitecompanionapp.composeapp.generated.resources.shop
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun drawerContent(
    navController: NavController,
    drawerState: DrawerState,
    coroutineScope: CoroutineScope,
    currentDestination: MutableState<Pair<String?, String?>>,
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
                        contentDescription = stringResource(Res.string.shop),
                    )
                },
                label = { Text(text = stringResource(Res.string.shop)) },
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
                        contentDescription = stringResource(Res.string.search),
                    )
                },
                label = { Text(text = stringResource(Res.string.search)) },
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
                        contentDescription = stringResource(Res.string.cosmetics),
                    )
                },
                label = { Text(text = stringResource(Res.string.battle_royale_cosmetics)) },
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
                        painter =
                        painterResource(Res.drawable.flag_icon),
                        contentDescription = stringResource(Res.string.banners),
                    )
                },
                label = { Text(text = stringResource(Res.string.banners)) },
                selected = currentDestination.value.first == NavigationItem.Banners.route,
                onClick = {
                    navController.navigate(route = NavigationGraphs.CosmeticsGraph.graph)
                    navController.navigate(NavigationItem.Banners.route)
                    coroutineScope.launch {
                        drawerState.close()
                    }
                }
            )
            NavigationDrawerItem(
                icon = {
                    Icon(
                        painter =
                        painterResource(Res.drawable.map_icon),
                        contentDescription = stringResource(Res.string.map),
                    )
                },
                label = { Text(text = stringResource(Res.string.map)) },
                selected = currentDestination.value.second == NavigationGraphs.MapGraph.graph,
                onClick = {
                    navController.navigate(route = NavigationGraphs.MapGraph.graph)
                    coroutineScope.launch {
                        drawerState.close()
                    }
                }
            )
        }
    }
}