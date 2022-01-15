package com.eneskayiklik.wallup.feature_home.presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.eneskayiklik.wallup.destinations.CollectionScreenDestination
import com.eneskayiklik.wallup.feature_home.domain.model.ColorItem
import com.eneskayiklik.wallup.feature_home.domain.model.HomeEvent
import com.ramcosta.composedestinations.spec.Direction

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
fun LazyListScope.colorSection(colors: List<ColorItem>, onEvent: (HomeEvent) -> Unit) {
    item(key = "color_section") {
        ColorSection(colors = colors) {
            onEvent(HomeEvent.Navigate(it))
        }
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
private fun LazyItemScope.ColorSection(
    colors: List<ColorItem>,
    onClick: (Direction) -> Unit
) {
    Column(
        modifier = Modifier.animateItemPlacement(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SectionTitle(
            title = "The color tone",
            modifier = Modifier.padding(start = 16.dp, top = 8.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(count = colors.size, key = { it }) {
                SingleColorItem(colors[it], onClick)
            }
        }
    }
}

@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
private fun SingleColorItem(item: ColorItem, onClick: (Direction) -> Unit) {
    Surface(
        onClick = {
            onClick(CollectionScreenDestination(item.name, item.name))
        },
        modifier = Modifier.size(50.dp),
        color = Color(android.graphics.Color.parseColor(item.hexCode)),
        shape = RoundedCornerShape(10.dp)
    ) {

    }
}