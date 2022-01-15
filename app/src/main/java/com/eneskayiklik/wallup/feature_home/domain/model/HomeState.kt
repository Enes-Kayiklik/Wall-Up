package com.eneskayiklik.wallup.feature_home.domain.model

data class HomeState(
    val colorList: List<ColorItem> = emptyList(),
    val categories: List<Category> = emptyList(),
    val randomPhotos: List<UnsplashPhoto>? = null
)