package com.eneskayiklik.wallup.feature_home.domain.model

import androidx.annotation.DrawableRes

data class Category(
    val title: String = "",
    @DrawableRes val imageRes: Int = -1,
)
