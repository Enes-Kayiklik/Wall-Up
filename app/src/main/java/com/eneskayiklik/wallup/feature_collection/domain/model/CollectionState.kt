package com.eneskayiklik.wallup.feature_collection.domain.model

import com.eneskayiklik.wallup.feature_home.domain.model.UnsplashPhoto

data class CollectionState(
    val title: String = "",
    val count: Int = 0,
    val items: List<UnsplashPhoto> = emptyList()
)
