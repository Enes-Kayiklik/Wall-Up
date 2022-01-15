package com.eneskayiklik.wallup.feature_detail.domain.model

import android.graphics.drawable.Drawable
import com.eneskayiklik.wallup.feature_home.domain.model.UnsplashPhoto

data class DetailState(
    val thumbnail: String? = null,
    val imageDrawable: Drawable? = null,
    val currentDownloadId: Long? = null,
    val imageDetail: UnsplashPhoto? = null
)
