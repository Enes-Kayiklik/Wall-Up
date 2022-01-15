package com.eneskayiklik.wallup.feature_detail.presentation.component

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.*
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.eneskayiklik.wallup.R
import com.eneskayiklik.wallup.feature_detail.domain.model.DetailEvent
import com.eneskayiklik.wallup.feature_detail.domain.model.DetailState
import com.eneskayiklik.wallup.feature_home.domain.model.PhotoDetail
import com.eneskayiklik.wallup.feature_home.domain.model.UnsplashPhoto
import com.eneskayiklik.wallup.feature_home.domain.model.UserDetail
import com.eneskayiklik.wallup.utils.const.INSTAGRAM_LINK
import com.eneskayiklik.wallup.utils.const.TWITTER_LINK
import com.eneskayiklik.wallup.utils.extensions.parseCount

@ExperimentalCoilApi
@ExperimentalUnitApi
@ExperimentalMaterialApi
fun LazyListScope.imageInfoItem(state: DetailState, onEvent: (DetailEvent) -> Unit) {
    item { ActionButtonGroup(onEvent, state.imageDrawable) }
    item { ImageInfoItem(state.imageDetail, onEvent) }
    detailRelatedCollection(state.imageDetail?.relatedCollections, onEvent)
    /*item { Divider(modifier = Modifier.height(1.dp)) }
    item { ExifInfoContainer(imageDetail.photoDetail) }*/
}

@ExperimentalUnitApi
@ExperimentalMaterialApi
@Composable
private fun ImageInfoItem(imageDetail: UnsplashPhoto?, onEvent: (DetailEvent) -> Unit) {
    if (imageDetail == null)
        return
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        UserSection(userDetail = imageDetail.userDetail, imageDetail.sourceUrl, onEvent)
        PropertySection(imageDetail)
    }
}

@Composable
private fun ActionButtonGroup(onEvent: (DetailEvent) -> Unit, drawable: Drawable?) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { onEvent(DetailEvent.Navigate(null)) }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                modifier = Modifier.size(24.dp),
                contentDescription = "Back",
                tint = MaterialTheme.colors.onBackground
            )
        }
        IconButton(onClick = { onEvent(DetailEvent.Share(drawable, context)) }) {
            Icon(
                imageVector = Icons.Default.Share,
                modifier = Modifier.size(24.dp),
                contentDescription = "Share with..",
                tint = MaterialTheme.colors.onBackground
            )
        }
    }
}

@ExperimentalUnitApi
@ExperimentalMaterialApi
@Composable
private fun UserSection(userDetail: UserDetail, unsplashSource: String, onEvent: (DetailEvent) -> Unit) {
    val ppPainter = rememberImagePainter(data = userDetail.image)
    val context = LocalContext.current
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Image(
                painter = ppPainter,
                contentDescription = "User Profile Photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(
                        CircleShape
                    )
            )
            Column(
                modifier = Modifier.height(64.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = userDetail.name,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    if (userDetail.unsplashProfile.isNotEmpty()) {
                        IconButton(onClick = {
                            onEvent(DetailEvent.ShareText(userDetail.unsplashProfile, context))
                        }, modifier = Modifier.size(24.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_unsplash),
                                modifier = Modifier.size(24.dp),
                                contentScale = ContentScale.Crop,
                                contentDescription = "Twitter"
                            )
                        }
                    }
                    if (userDetail.instaUserName.isNotEmpty()) {
                        IconButton(onClick = {
                            onEvent(
                                DetailEvent.ShareText(
                                    INSTAGRAM_LINK.plus(
                                        userDetail.instaUserName
                                    ), context
                                )
                            )
                        }, modifier = Modifier.size(24.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_instagram),
                                modifier = Modifier.size(24.dp),
                                contentScale = ContentScale.Crop,
                                contentDescription = "Instagram"
                            )
                        }
                    }
                    if (userDetail.twitterUserName.isNotEmpty()) {
                        IconButton(onClick = {
                            onEvent(
                                DetailEvent.ShareText(
                                    TWITTER_LINK.plus(
                                        userDetail.twitterUserName
                                    ), context
                                )
                            )
                        }, modifier = Modifier.size(24.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_twitter),
                                modifier = Modifier.size(24.dp),
                                contentScale = ContentScale.Crop,
                                contentDescription = "Twitter"
                            )
                        }
                    }
                }
            }
        }
        TextButton(onClick = { onEvent(
            DetailEvent.ShareText(
                unsplashSource, context
            )
        ) }) {
            val text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp)) {
                    append("Show source\n")
                }
                withStyle(SpanStyle(fontWeight = FontWeight.SemiBold, fontSize = 11.sp)) {
                    append("Unsplash.com")
                }
            }
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
                letterSpacing = TextUnit(0F, TextUnitType.Sp)
            )
        }
    }
}

@Composable
private fun PropertySection(imageDetail: UnsplashPhoto) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        SinglePropertyItem(value = imageDetail.views, title = "Views")
        SinglePropertyItem(value = imageDetail.downloads, title = "Downloads")
        SinglePropertyItem(value = imageDetail.likes, title = "Likes")
    }
}

@Composable
private fun ExifInfoContainer(imageDetail: PhotoDetail) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(48.dp)
        ) {
            SingleExifItem(icon = Icons.Default.Screenshot, title = imageDetail.resolution)
            SingleExifItem(icon = Icons.Default.Dock, title = imageDetail.aperture)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(48.dp)
        ) {
            SingleExifItem(icon = Icons.Default.Camera, title = imageDetail.camera)
            SingleExifItem(icon = Icons.Default.Exposure, title = imageDetail.exposureTime)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(48.dp)
        ) {
            SingleExifItem(icon = Icons.Default.Dock, title = imageDetail.focalLength)
            SingleExifItem(icon = Icons.Default.Iso, title = imageDetail.iso)
        }
    }
}

@Composable
private fun SingleExifItem(icon: ImageVector, title: String) {
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        Image(imageVector = icon, contentDescription = icon.name)
        Text(text = title)
    }
}

@Composable
private fun SinglePropertyItem(value: Int, title: String) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colors.surface)
            .padding(vertical = 12.dp, horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value.parseCount(),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground
        )
        Text(
            text = title,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.onBackground
        )
    }
}
