package com.eneskayiklik.wallup.feature_collection.data.dto

import com.eneskayiklik.wallup.feature_home.data.dto.UnsplashPhotoDto
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponseDto(
    val results: List<UnsplashPhotoDto>,
    val total: Int,
    val total_pages: Int
)