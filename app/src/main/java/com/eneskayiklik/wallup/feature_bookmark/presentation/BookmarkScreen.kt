package com.eneskayiklik.wallup.feature_bookmark.presentation


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.eneskayiklik.wallup.feature_bookmark.presentation.component.EmptyBookmarkSection
import com.eneskayiklik.wallup.feature_bookmark.presentation.component.bookmarkSection
import com.eneskayiklik.wallup.feature_collection.presentation.component.titleSection
import com.eneskayiklik.wallup.ui.animation.ScreensAnim
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalAnimationApi
@ExperimentalUnitApi
@Destination(
    style = ScreensAnim::class
)
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun BookmarkScreen(
    navigator: DestinationsNavigator,
    viewModel: BookmarkViewModel = hiltViewModel()
) {
    val state = viewModel.bookmarkState.collectAsState().value
    val scrollState = rememberLazyListState()
    if (state.count == 0) {
        EmptyBookmarkSection()
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            contentPadding = PaddingValues(top = 60.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            state = scrollState
        ) {
            titleSection(state.title, state.count, modifier = Modifier.padding(horizontal = 16.dp))
            bookmarkSection(state.items) {
                navigator.navigate(it)
            }
        }
    }
}