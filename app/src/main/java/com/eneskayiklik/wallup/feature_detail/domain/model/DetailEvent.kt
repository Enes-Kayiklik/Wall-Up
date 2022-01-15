package com.eneskayiklik.wallup.feature_detail.domain.model

import android.content.Context
import android.graphics.drawable.Drawable

sealed class DetailEvent {
    data class OnBookmarkClick(
        val id: String,
        val thumbnail: String,
        val color: String,
        val addBookmark: Boolean
    ) : DetailEvent()

    data class OnDownloadClick(val url: String, val createdAt: String) : DetailEvent()
    data class OnWallpaper(
        val context: Context,
        val bitmap: Drawable?
    ) : DetailEvent()

    data class Navigate(val id: String?, val title: String? = null) : DetailEvent()
    data class UpdateDrawable(val drawable: Drawable?) : DetailEvent()
    data class Share(val drawable: Drawable?, val context: Context) : DetailEvent()
    data class ShareText(val data: String, val context: Context) : DetailEvent()
}
