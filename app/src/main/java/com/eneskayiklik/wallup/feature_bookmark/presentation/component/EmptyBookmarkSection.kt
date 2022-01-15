package com.eneskayiklik.wallup.feature_bookmark.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eneskayiklik.wallup.R

@Composable
fun EmptyBookmarkSection() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_empty_bookmark),
                contentDescription = "Empty Bookmark"
            )
            Text(
                text = "Nothing!", style = MaterialTheme.typography.h1.copy(
                    color = MaterialTheme.colors.onBackground
                )
            )
            Text(
                text = "Your bookmark list is empty.", style = MaterialTheme.typography.h6.copy(
                    color = MaterialTheme.colors.onBackground
                )
            )
        }
    }
}