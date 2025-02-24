package org.smartmuseum.fortnitecompanion.ui.screens.cosmetics

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import org.lighthousegames.logging.KmLog
import org.smartmuseum.fortnitecompanion.data.cosmetics.CosmeticEnum
import org.smartmuseum.fortnitecompanion.data.cosmetics.ICosmetic
import org.smartmuseum.fortnitecompanion.networking.NetworkResult
import org.smartmuseum.fortnitecompanion.ui.screens.generic.ErrorScreen
import org.smartmuseum.fortnitecompanion.ui.screens.generic.LoadingScreen

@Composable
fun CosmeticListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    cosmeticEnum: CosmeticEnum,
    refresh: () -> Unit,
    state: State<NetworkResult<List<ICosmetic>>>,
    onSelectCosmetic: (ICosmetic) -> Unit
) {
    val log: KmLog = koinInject<KmLog> { parametersOf("CosmeticListScreen-${cosmeticEnum.name}") }
    LaunchedEffect(true) {
        if (state.value is NetworkResult.Error ||
            state.value is NetworkResult.Ready
        )
            refresh()
    }
    when (state.value) {
        is NetworkResult.Error -> ErrorScreen(onRetry = {
            refresh()
        })

        NetworkResult.Loading, NetworkResult.Ready -> LoadingScreen(modifier = modifier)
        is NetworkResult.Success -> {
            val groupedByRarity: Map<String?, List<ICosmetic>> =
                (state.value as NetworkResult.Success<List<ICosmetic>>)
                    .data.groupBy { it.getCosmeticRarity()?.displayValue }
            for (entry in groupedByRarity) {
                log.i { "Rarity: ${entry.key} has ${entry.value.size} items" }
            }
            CosmeticsList(
                navController = navController,
                modifier = modifier,
                cosmeticsMap = groupedByRarity,
                onSelectCosmetic = onSelectCosmetic
            )
        }
    }
}
