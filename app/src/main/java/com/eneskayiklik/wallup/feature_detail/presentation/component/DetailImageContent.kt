package com.eneskayiklik.wallup.feature_detail.presentation.component

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalCoilApi
@Composable
fun DetailImageContent(
    painter: ImagePainter,
    thumbnailPainter: ImagePainter,
    modifier: Modifier = Modifier
) {
    val isLoading = painter.state !is ImagePainter.State.Success
    val thumbnailImageScale = if (isLoading) 1.05F else 0F
    val actualImageScale by animateFloatAsState(
        targetValue = if (isLoading) 1.05F else 1F,
        animationSpec = tween(durationMillis = 500)
    )
    val thumbnailImageState = rememberScrollState()
    val actualImageState = rememberScrollState()
    rememberCoroutineScope().launch {
        if (isLoading)
            thumbnailImageState.scrollTo(thumbnailImageState.maxValue / 2)
        else
            actualImageState.scrollTo(actualImageState.maxValue / 2)
    }
    Image(
        painter = thumbnailPainter,
        contentScale = ContentScale.FillHeight,
        contentDescription = "Full Image",
        modifier = modifier
            .scale(scaleX = thumbnailImageScale, scaleY = 1F)
            .horizontalScroll(thumbnailImageState),
    )
    Image(
        painter = painter,
        contentScale = ContentScale.FillHeight,
        contentDescription = "Full Image",
        modifier = modifier
            .scale(scaleX = actualImageScale, scaleY = 1F)
            .horizontalScroll(actualImageState),
    )

    if (isLoading) {
        Box(modifier = modifier) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .align(Alignment.BottomCenter),
                color = Color.White
            )
        }
    }
}