package com.eneskayiklik.wallup.feature_collection.presentation.component

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.eneskayiklik.wallup.feature_home.domain.model.UnsplashPhoto
import com.eneskayiklik.wallup.ui.navigation.Destinations
import com.eneskayiklik.wallup.utils.extensions.gridItems
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
fun LazyListScope.itemsSection(
    categories: List<UnsplashPhoto>,
    navigator: DestinationsNavigator
) {
    gridItems(
        data = categories,
        columnCount = 2,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        SingleSearchResultItem(it, navigator)
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
private fun SingleSearchResultItem(data: UnsplashPhoto, navigator: DestinationsNavigator) {
    var isClicked by remember { mutableStateOf(false) }
    val scaleAnim by animateFloatAsState(
        targetValue = if (isClicked) 1.05F else 1F,
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = Spring.DampingRatioLowBouncy
        ),
    )
    Surface(
        modifier = Modifier
            .size(width = 150.dp, height = 250.dp)
            .scale(scaleAnim),
        onClick = {
            isClicked = isClicked.not()
            val encodedUrl = URLEncoder.encode(data.smallImage, StandardCharsets.UTF_8.toString())
            //onClick("${Destinations.Detail.route}/${data.id}?thumbnail=${encodedUrl}")
        },
        elevation = 2.dp,
        color = Color(android.graphics.Color.parseColor(data.color)),
        shape = RoundedCornerShape(10.dp)
    ) {
        val painter = rememberImagePainter(data = data.smallImage) {
            crossfade(350)
        }

        Image(
            painter = painter,
            contentScale = ContentScale.Crop,
            contentDescription = data.userDetail.name
        )
    }
}