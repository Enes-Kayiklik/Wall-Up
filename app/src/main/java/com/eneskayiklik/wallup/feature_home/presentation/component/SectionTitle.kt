package com.eneskayiklik.wallup.feature_home.presentation.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun SectionTitle(
    modifier: Modifier = Modifier,
    title: String
) {
    Text(
        text = title,
        style = MaterialTheme.typography.h6,
        modifier = modifier
    )
}