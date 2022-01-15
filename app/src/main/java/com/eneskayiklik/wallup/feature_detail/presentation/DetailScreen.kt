package com.eneskayiklik.wallup.feature_detail.presentation

import android.app.DownloadManager
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.eneskayiklik.wallup.feature_detail.domain.model.DetailScreenNavArgs
import com.eneskayiklik.wallup.feature_detail.presentation.component.imageInfoItem
import com.eneskayiklik.wallup.feature_detail.presentation.component.imageItem
import com.eneskayiklik.wallup.ui.animation.ScreensAnim
import com.eneskayiklik.wallup.utils.broadcast_receiver.SystemBroadcastReceiver
import com.eneskayiklik.wallup.utils.model.UiEvent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

@ExperimentalFoundationApi
@Destination(
    navArgsDelegate = DetailScreenNavArgs::class,
    style = ScreensAnim::class
)
@ExperimentalUnitApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun DetailScreen(
    navigator: DestinationsNavigator,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val detailState = viewModel.detailState.collectAsState().value
    val color = detailState.imageDetail?.color
    val thumbnail = detailState.thumbnail ?: detailState.imageDetail?.smallImage
    val mColor by animateColorAsState(
        targetValue = if (color.isNullOrEmpty()
                .not()
        ) Color(android.graphics.Color.parseColor(color)) else MaterialTheme.colors.background
    )
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest {
            when (it) {
                is UiEvent.OnNavigate -> navigator.navigate(it.route)
                is UiEvent.ShowToast -> Toast.makeText(context, it.title, Toast.LENGTH_LONG).show()
                UiEvent.PopBack -> navigator.popBackStack()
                else -> {}
            }
        }
    }
    SystemBroadcastReceiver(
        systemAction = DownloadManager.ACTION_DOWNLOAD_COMPLETE,
    ) { intent ->
        val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L)
        if (id == viewModel.mDownloadId && id != null) {
            viewModel.downloadComplete()
        }
    }
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize(),
        contentPadding = PaddingValues(bottom = 12.dp)
    ) {
        imageItem(mColor, thumbnail, detailState, viewModel::onEvent)
        if (detailState.imageDetail != null) {
            imageInfoItem(detailState, viewModel::onEvent)
        }
    }
}