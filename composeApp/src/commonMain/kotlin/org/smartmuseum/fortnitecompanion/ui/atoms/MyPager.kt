package org.smartmuseum.fortnitecompanion.ui.atoms

import androidx.compose.runtime.Composable

expect class MyPager() {
    @Composable
    fun Pager(count: Int, content: @Composable (Int) -> Unit)
}