package com.eneskayiklik.wallup.feature_collection.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

fun LazyListScope.titleSection(title: String, count: Int, modifier: Modifier = Modifier) {
    item {
        Column(modifier = modifier) {
            if (title.isNotEmpty()) {
                TitleSection(title = title)
            }
            if (count != 0) {
                CountSection(count = count)
            }
        }
    }

}

@Composable
private fun TitleSection(title: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier, text = title, style = MaterialTheme.typography.h6.copy(
            color = MaterialTheme.colors.onBackground,
            fontSize = 32.sp
        )
    )
}

@Composable
private fun CountSection(count: Int, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = "$count wallpaper available",
        style = MaterialTheme.typography.body1.copy(
            color = MaterialTheme.colors.onBackground
        )
    )
}