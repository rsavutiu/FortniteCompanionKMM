package org.smartmuseum.fortnitecompanion

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import org.smartmuseum.fortnitecompanion.data.cosmetics.Cosmetic
import org.smartmuseum.fortnitecompanion.ui.FortniteCosmetic
import org.smartmuseum.fortnitecompanion.usecases.GetCosmeticsUseCase
import org.smartmuseum.fortnitecompanion.viewmodel.CosmeticsViewModel

@Composable
@Preview
fun App() {
    val cosmeticsViewModel: CosmeticsViewModel = koinInject()
//    var cosmeticsList: List<Cosmetic> = remember { listOf() }
    val getCosmeticsUseCase: GetCosmeticsUseCase = koinInject()
    val battleRoyalState: State<List<Cosmetic>> = cosmeticsViewModel.battleRoyaleCosmetics.collectAsState()
    MaterialTheme {
        LaunchedEffect(true) {
//            cosmeticsList = getCosmeticsUseCase.getCosmetics()
            cosmeticsViewModel.getBattleRoyaleCosmetics()
        }
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                LazyColumn(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    item {
                        Text("Compose: $greeting")
                    }
                    battleRoyalState.value.forEach { cosmetic ->
                        item {
                            FortniteCosmetic(fortniteCosmetic = cosmetic)
                        }
                    }
                }
            }
        }
    }
}