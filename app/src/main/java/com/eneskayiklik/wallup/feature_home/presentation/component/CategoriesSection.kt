package com.eneskayiklik.wallup.feature_home.presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.eneskayiklik.wallup.destinations.CollectionScreenDestination
import com.eneskayiklik.wallup.feature_home.domain.model.Category
import com.eneskayiklik.wallup.feature_home.domain.model.HomeEvent
import com.eneskayiklik.wallup.utils.extensions.gridItems
import com.ramcosta.composedestinations.spec.Direction

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
fun LazyListScope.categoriesSection(
    categories: List<Category>,
    onEvent: (HomeEvent) -> Unit
) {
    item(key = "categories_section") {
        SectionTitle(
            title = "Categories",
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp)
        )
    }
    gridItems(
        data = categories,
        columnCount = 2,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        SingleCategoryItem(it) { route ->
            onEvent(HomeEvent.Navigate(route))
        }
    }
}

@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
private fun SingleCategoryItem(item: Category, onClick: (Direction) -> Unit) {
    Surface(
        onClick = { onClick(CollectionScreenDestination(item.title, item.title)) },
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .scale(2F)
            )
            Text(
                text = item.title,
                style = MaterialTheme.typography.h6.copy(
                    color = Color.White
                )
            )
        }
    }
}