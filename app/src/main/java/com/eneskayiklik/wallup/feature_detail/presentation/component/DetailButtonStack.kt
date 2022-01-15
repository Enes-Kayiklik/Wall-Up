package com.eneskayiklik.wallup.feature_detail.presentation.component

import android.graphics.drawable.Drawable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.eneskayiklik.wallup.feature_detail.domain.model.DetailEvent

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun DetailButtonStack(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    isBookmarked: Boolean,
    isDownloading: Boolean,
    imageBitmap: Drawable?,
    imageUrl: String,
    thumbnailUrl: String,
    imageId: String,
    createdAt: String,
    buttonsBackColor: String?,
    onEvent: (DetailEvent) -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = isVisible,
        enter = scaleIn(
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BookmarkButtonContainer(
                color = buttonsBackColor,
                isBookmarked = isBookmarked,
                thumbnailUrl = thumbnailUrl,
                id = imageId,
                onEvent = onEvent
            )
            DownloadButtonContainer(
                color = buttonsBackColor,
                isDownloading = isDownloading,
                imageUrl = imageUrl,
                createdAt = createdAt,
                onEvent = onEvent
            )
            BrushButtonContainer(
                color = buttonsBackColor,
                imageBitmap = imageBitmap,
                onEvent = onEvent
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun BookmarkButtonContainer(
    color: String?,
    isBookmarked: Boolean,
    id: String,
    thumbnailUrl: String,
    onEvent: (DetailEvent) -> Unit
) {
    val animateRotation by animateFloatAsState(
        targetValue = if (isBookmarked) 360F else 0F,
        animationSpec = spring()
    )
    val icon = if (isBookmarked.not()) Icons.Default.BookmarkAdd else Icons.Default.BookmarkRemove
    SingleButton(icon = icon, modifier = Modifier.rotate(animateRotation), color = color) {
        onEvent(DetailEvent.OnBookmarkClick(id, thumbnailUrl, color ?: "", isBookmarked.not()))
    }
}

@ExperimentalMaterialApi
@Composable
private fun DownloadButtonContainer(
    color: String?, isDownloading: Boolean, imageUrl: String,
    createdAt: String, onEvent: (DetailEvent) -> Unit
) {
    val scaleAnim by animateFloatAsState(targetValue = if (isDownloading) 1F else 0F)
    Box(contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(50.dp)
                .scale(scaleAnim),
            color = Color.White
        )
        SingleButton(icon = Icons.Default.Download, color = color) {
            if (isDownloading.not())
                onEvent(DetailEvent.OnDownloadClick(imageUrl, createdAt))
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun BrushButtonContainer(
    color: String?,
    imageBitmap: Drawable?,
    onEvent: (DetailEvent) -> Unit
) {
    val context = LocalContext.current
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        SingleButton(
            icon = Icons.Default.Brush,
            color = color
        ) { onEvent(
            DetailEvent.OnWallpaper(
                context,
                imageBitmap
            )
        ) }
    }
}

@ExperimentalMaterialApi
@Composable
private fun SingleButton(
    icon: ImageVector,
    color: String?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val mColor = if (color.isNullOrEmpty()
            .not()
    ) Color(android.graphics.Color.parseColor(color)).copy(alpha = 0.5F) else MaterialTheme.colors.background
    Surface(
        modifier = Modifier.size(50.dp),
        shape = RoundedCornerShape(10.dp),
        color = mColor,
        onClick = onClick
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = modifier
                    .size(25.dp)
                    .align(Alignment.Center),
                imageVector = icon,
                colorFilter = ColorFilter.tint(Color.White),
                contentDescription = icon.name
            )
        }
    }
}