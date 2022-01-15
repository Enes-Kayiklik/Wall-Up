package com.eneskayiklik.wallup.feature_home.presentation.component

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.eneskayiklik.wallup.destinations.BookmarkScreenDestination
import com.eneskayiklik.wallup.feature_home.domain.model.HomeEvent
import com.eneskayiklik.wallup.ui.navigation.Destinations

@ExperimentalAnimationApi
@ExperimentalUnitApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
fun LazyListScope.welcomeSection(onEvent: (HomeEvent) -> Unit) {
    item(key = "welcome_section") { WelcomeSection(onEvent) }
}

@ExperimentalUnitApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
private fun WelcomeSection(onEvent: (HomeEvent) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Welcome to WallUp", style = MaterialTheme.typography.h4.copy(
                    color = MaterialTheme.colors.onBackground
                )
            )
            Text(
                text = "Discover Unsplash photos and find best wallpaper for you.",
                style = MaterialTheme.typography.subtitle1.copy(
                    color = MaterialTheme.colors.onBackground
                )
            )
        }
        IconButton(modifier = Modifier.size(40.dp), onClick = {
            onEvent(
                HomeEvent.Navigate(BookmarkScreenDestination)
            )
        }) {
            Icon(
                imageVector = Icons.Default.Bookmarks,
                contentDescription = "Bookmarks",
                tint = MaterialTheme.colors.onBackground
            )
        }
    }
}