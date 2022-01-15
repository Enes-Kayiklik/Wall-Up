package com.eneskayiklik.wallup.feature_collection.presentation


import androidx.compose.animation.*
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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.eneskayiklik.wallup.feature_collection.domain.model.CollectionScreenNavArgs
import com.eneskayiklik.wallup.feature_collection.presentation.component.itemsSection
import com.eneskayiklik.wallup.feature_collection.presentation.component.titleSection
import com.eneskayiklik.wallup.ui.navigation.Destinations
import com.google.accompanist.navigation.animation.composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalAnimationApi
@ExperimentalUnitApi
@Destination(
    navArgsDelegate = CollectionScreenNavArgs::class
)
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun CollectionScreen(
    navigator: DestinationsNavigator,
    viewModel: CollectionViewModel = hiltViewModel()
) {
    val state = viewModel.collectionState.collectAsState().value
    val scrollState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentPadding = PaddingValues(top = 60.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = scrollState
    ) {
        titleSection(state.title, state.count, modifier = Modifier.padding(horizontal = 16.dp))
        itemsSection(state.items, navigator)
    }
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
fun NavGraphBuilder.collectionScreen(onNavigate: (String) -> Unit) {
    composable(Destinations.CollectionWithArgs.route, arguments = listOf(
        navArgument("collectionId") {
            defaultValue = null; nullable = true; type = NavType.StringType
        },
        navArgument("title") {
            defaultValue = null; nullable = true; type = NavType.StringType
        },
        navArgument("searchQuery") {
            defaultValue = null; nullable = true; type = NavType.StringType
        },
    ), enterTransition = { scaleIn(initialScale = .7F) + fadeIn(initialAlpha = .5F) },
        exitTransition = { scaleOut(targetScale = .7F) + fadeOut(targetAlpha = .5F) }) {
        //CollectionScreen(onNavigate)
    }
}