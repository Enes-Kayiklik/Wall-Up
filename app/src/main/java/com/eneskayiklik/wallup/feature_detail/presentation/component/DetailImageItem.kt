package com.eneskayiklik.wallup.feature_detail.presentation.component

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.eneskayiklik.wallup.feature_detail.domain.model.DetailEvent
import com.eneskayiklik.wallup.feature_detail.domain.model.DetailState

@ExperimentalCoilApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
fun LazyListScope.imageItem(
    mColor: Color,
    thumbnail: String?,
    detailState: DetailState,
    onEvent: (DetailEvent) -> Unit
) {
    item {
        ImageItem(
            mColor = mColor,
            thumbnail = thumbnail,
            detailState = detailState,
            onEvent = onEvent
        )
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
private fun ImageItem(
    mColor: Color,
    thumbnail: String?,
    detailState: DetailState,
    onEvent: (DetailEvent) -> Unit
) {
    val localeConfig = LocalConfiguration.current
    Box(
        modifier = Modifier
            .size(
                width = localeConfig.screenWidthDp.dp + 5.dp,
                height = localeConfig.screenHeightDp.dp - 48.dp
            )
            .background(mColor)
    ) {
        val imageUrl = detailState.imageDetail?.fullImage
        val painter = rememberImagePainter(data = imageUrl)
        val thumbnailPainter = rememberImagePainter(data = thumbnail)
        onEvent(DetailEvent.UpdateDrawable((painter.state as? ImagePainter.State.Success)?.result?.drawable))
        DetailImageContent(
            painter = painter,
            thumbnailPainter = thumbnailPainter,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        )
        DetailButtonStack(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 24.dp),
            imageUrl = imageUrl ?: "",
            thumbnailUrl = thumbnail ?: "",
            isBookmarked = detailState.imageDetail?.isBookmarked ?: false,
            imageId = detailState.imageDetail?.id ?: "",
            imageBitmap = detailState.imageDrawable,
            isDownloading = detailState.currentDownloadId != null,
            createdAt = detailState.imageDetail?.createdAt ?: "",
            isVisible = painter.state is ImagePainter.State.Success,
            buttonsBackColor = detailState.imageDetail?.color,
            onEvent = onEvent
        )
    }
}