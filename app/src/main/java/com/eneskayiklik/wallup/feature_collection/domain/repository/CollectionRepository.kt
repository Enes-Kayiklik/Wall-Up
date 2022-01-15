package com.eneskayiklik.wallup.feature_collection.domain.repository

import com.eneskayiklik.wallup.feature_home.data.dto.UnsplashPhotoDto
import com.eneskayiklik.wallup.utils.network.Resource

interface CollectionRepository {

    suspend fun getCollectionData(collectionId: String): Resource<List<UnsplashPhotoDto>>
}