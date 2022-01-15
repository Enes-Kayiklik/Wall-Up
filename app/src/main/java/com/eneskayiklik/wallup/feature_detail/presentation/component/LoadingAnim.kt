package com.eneskayiklik.wallup.feature_detail.presentation.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoadingAnim(
    color: Color,
    modifier: Modifier = Modifier,
    lineWidth: Dp = 5.dp,
    lineSpace: Dp = 4.dp,
    lineRadius: Dp = lineWidth,
    animationDuration: Int = 500,
    lineMinSize: Dp = 20.dp,
    lineMaxSize: Dp = 100.dp
) {
    val infiniteTransition = rememberInfiniteTransition()
    val heightAnim by infiniteTransition.animateFloat(
        initialValue = lineMinSize.value,
        targetValue = lineMaxSize.value,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val heightAnimSecond by infiniteTransition.animateFloat(
        initialValue = lineMaxSize.value,
        targetValue = lineMinSize.value,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val topLeftAnim by infiniteTransition.animateFloat(
        initialValue = lineMaxSize.value / 2 - lineMinSize.value / 2,
        targetValue = 0F,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val topLeftAnimSecond by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = lineMaxSize.value / 2 - lineMinSize.value / 2,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    Canvas(modifier = modifier.size(width = lineWidth * 6 + lineSpace * 5, height = lineMaxSize)) {
        val value = size.width / 6
        val lineWidthPx = lineWidth.toPx()
        val lineRadiusPx = lineRadius.toPx()
        drawRoundRect(
            color = color,
            topLeft = Offset(0F, topLeftAnim),
            size = Size(lineWidthPx, heightAnim),
            cornerRadius = CornerRadius(x = lineRadiusPx, y = lineRadiusPx),
        )
        drawRoundRect(
            color = color,
            topLeft = Offset(value, topLeftAnimSecond),
            size = Size(lineWidthPx, heightAnimSecond),
            cornerRadius = CornerRadius(x = lineRadiusPx, y = lineRadiusPx),
        )
        drawRoundRect(
            color = color,
            topLeft = Offset(2 * value, topLeftAnim),
            size = Size(lineWidthPx, heightAnim),
            cornerRadius = CornerRadius(x = lineRadiusPx, y = lineRadiusPx),
        )
        drawRoundRect(
            color = color,
            topLeft = Offset(3 * value, topLeftAnimSecond),
            size = Size(lineWidthPx, heightAnimSecond),
            cornerRadius = CornerRadius(x = lineRadiusPx, y = lineRadiusPx),
        )
        drawRoundRect(
            color = color,
            topLeft = Offset(4 * value, topLeftAnim),
            size = Size(lineWidthPx, heightAnim),
            cornerRadius = CornerRadius(x = lineRadiusPx, y = lineRadiusPx),
        )
    }
}

@Preview
@Composable
fun LoadingPrev() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LoadingAnim(Color.Blue)
    }
}