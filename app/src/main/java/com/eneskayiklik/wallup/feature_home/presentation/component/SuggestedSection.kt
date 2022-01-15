package com.eneskayiklik.wallup.feature_home.presentation.component

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.eneskayiklik.wallup.destinations.DetailScreenDestination
import com.eneskayiklik.wallup.feature_home.domain.model.HomeEvent
import com.eneskayiklik.wallup.feature_home.domain.model.UnsplashPhoto
import com.ramcosta.composedestinations.spec.Direction
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@ExperimentalAnimationApi
@ExperimentalUnitApi
@ExperimentalFoundationApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
fun LazyListScope.suggestedSection(data: List<UnsplashPhoto>?, onEvent: (HomeEvent) -> Unit) {
    if (data != null) item(key = "suggested_section") {
        SuggestedSection(data) { route ->
            onEvent(HomeEvent.Navigate(route))
        }
    }
}

@ExperimentalUnitApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
private fun LazyItemScope.SuggestedSection(photos: List<UnsplashPhoto>, onClick: (Direction) -> Unit) {
    Column(
        modifier = Modifier.animateItemPlacement(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SectionTitle(
            title = "Suggested for you",
            modifier = Modifier.padding(start = 16.dp, top = 8.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(count = photos.size, key = { it }) {
                SingleSuggestedItem(photos[it], onClick)
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalUnitApi
@ExperimentalFoundationApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
private fun SingleSuggestedItem(data: UnsplashPhoto, onClick: (Direction) -> Unit) {
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
            onClick(DetailScreenDestination(id = data.id, thumbnail = encodedUrl))
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