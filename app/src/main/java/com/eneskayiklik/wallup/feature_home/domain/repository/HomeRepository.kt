package com.eneskayiklik.wallup.feature_home.domain.repository

import com.eneskayiklik.wallup.feature_home.data.dto.UnsplashPhotoDto
import com.eneskayiklik.wallup.feature_home.domain.model.Category
import com.eneskayiklik.wallup.feature_home.domain.model.ColorItem
import com.eneskayiklik.wallup.utils.network.Resource

interface HomeRepository {

    suspend fun getColorList(): List<ColorItem>

    suspend fun getCategoryList(): List<Category>

    suspend fun getRandomPhotos(): Resource<List<UnsplashPhotoDto>>
}