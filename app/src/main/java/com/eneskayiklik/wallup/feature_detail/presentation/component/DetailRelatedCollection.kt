package com.eneskayiklik.wallup.feature_detail.presentation.component

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.eneskayiklik.wallup.feature_detail.domain.model.DetailEvent
import com.eneskayiklik.wallup.feature_home.domain.model.RelatedCollectionResult
import com.eneskayiklik.wallup.feature_home.domain.model.RelatedCollections
import com.eneskayiklik.wallup.feature_home.presentation.component.SectionTitle

@ExperimentalCoilApi
@ExperimentalMaterialApi
fun LazyListScope.detailRelatedCollection(
    relatedCollection: RelatedCollections?,
    onEvent: (DetailEvent) -> Unit
) {
    if (relatedCollection == null)
        return
    item {
        SectionTitle(
            title = "Related Collections",
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
    }
    items(relatedCollection.total, key = { it.hashCode() }) {
        SingleRelatedCollectionItem(data = relatedCollection.collections[it]) { id, title ->
            onEvent(DetailEvent.Navigate(id, title))
        }
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
private fun SingleRelatedCollectionItem(
    data: RelatedCollectionResult,
    onClick: (id: String, title: String) -> Unit
) {
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
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .scale(scaleAnim),
        onClick = {
            isClicked = isClicked.not()
            onClick(data.id, data.title)
        },
        elevation = 2.dp,
        color = Color(android.graphics.Color.parseColor(data.color)),
        shape = RoundedCornerShape(10.dp)
    ) {
        val painter = rememberImagePainter(data = data.coverPhoto) {
            crossfade(350)
        }
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painter,
                contentScale = ContentScale.Crop,
                contentDescription = data.title
            )

            Text(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
                text = data.title,
                color = Color.White,
                style = MaterialTheme.typography.h6.copy(
                    shadow = Shadow(Color.Black, offset = Offset(1F, 1F))
                )
            )
        }
    }
}