package org.smartmuseum.fortnitecompanion

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import org.smartmuseum.fortnitecompanion.data.cosmetics.Cosmetic
import org.smartmuseum.fortnitecompanion.data.cosmetics.CosmeticsResponse
import org.smartmuseum.fortnitecompanion.di.commonModule
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
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Compose: $greeting")
                    battleRoyalState.value.forEach { cosmetic ->
                        Text(cosmetic.name ?: cosmetic.id)
                    }
                }
            }
        }
    }
}