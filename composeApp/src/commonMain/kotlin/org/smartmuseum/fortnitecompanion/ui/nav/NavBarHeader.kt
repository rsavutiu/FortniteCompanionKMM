package org.smartmuseum.fortnitecompanion.ui.nav

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NavBarHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Image(
//            painter = painterResource(resources.images.app_icon), contentDescription = stringResource(
//                resources.strings.app_name),
//            modifier = Modifier
//                .size(96.dp)
//                .padding(top = 10.dp)
//        )
        Text(
            text = "App name",
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}