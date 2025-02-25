package org.smartmuseum.fortnitecompanion.ui.previews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import org.smartmuseum.fortnitecompanion.ui.screens.cosmetics.FindPlayerStatsScreen
import org.smartmuseum.fortnitecompanion.ui.themes.AppTheme

@Composable
@Preview(showBackground = true, showSystemUi = true)
@PreviewLightDark()
fun FindPlayerStatsPreview() {
    AppTheme {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(48.dp))
//            Spacer(modifier = Modifier.height(48.dp))
//            Spacer(modifier = Modifier.background(color= Color.Blue).height(48.dp).fillMaxWidth())
            FindPlayerStatsScreen()
        }
    }
}
