package com.eneskayiklik.wallup.feature_home.presentation

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.eneskayiklik.wallup.feature_home.domain.model.HomeEvent
import com.eneskayiklik.wallup.feature_home.presentation.component.categoriesSection
import com.eneskayiklik.wallup.feature_home.presentation.component.colorSection
import com.eneskayiklik.wallup.feature_home.presentation.component.suggestedSection
import com.eneskayiklik.wallup.feature_home.presentation.component.welcomeSection
import com.eneskayiklik.wallup.utils.broadcast_receiver.ShakeManager
import com.eneskayiklik.wallup.utils.model.UiEvent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

@ExperimentalUnitApi
@ExperimentalAnimationApi
@Destination
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.homeState.collectAsState().value
    val scrollState = rememberLazyListState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest {
            when (it) {
                is UiEvent.OnNavigate -> navigator.navigate(it.route)
                is UiEvent.ScrollTop -> scrollState.animateScrollToItem(0)
                else -> {}
            }
        }
    }
    ShakeManager(systemAction = Context.SENSOR_SERVICE) {
        viewModel.onEvent(HomeEvent.ScrollTop)
    }
    LazyColumn(
        modifier = Modifier.background(MaterialTheme.colors.background),
        contentPadding = PaddingValues(top = 50.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = scrollState
    ) {
        welcomeSection(viewModel::onEvent)
        suggestedSection(state.randomPhotos, viewModel::onEvent)
        colorSection(state.colorList, viewModel::onEvent)
        categoriesSection(state.categories, viewModel::onEvent)
    }
}