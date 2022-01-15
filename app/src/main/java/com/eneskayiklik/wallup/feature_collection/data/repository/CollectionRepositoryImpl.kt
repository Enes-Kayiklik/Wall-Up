package com.eneskayiklik.wallup.feature_collection.data.repository

import com.eneskayiklik.wallup.BuildConfig
import com.eneskayiklik.wallup.feature_collection.domain.repository.CollectionRepository
import com.eneskayiklik.wallup.feature_home.data.dto.UnsplashPhotoDto
import com.eneskayiklik.wallup.utils.network.HttpParam
import com.eneskayiklik.wallup.utils.network.HttpRoutes
import com.eneskayiklik.wallup.utils.network.Resource
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class CollectionRepositoryImpl @Inject constructor(
    private val client: HttpClient
): CollectionRepository {

    override suspend fun getCollectionData(collectionId: String): Resource<List<UnsplashPhotoDto>> {
        return try {
            val data: List<UnsplashPhotoDto> = client.get {
                url(HttpRoutes.COLLECTION.replace("{id}", collectionId))
                parameter(HttpParam.CLIENT_ID, BuildConfig.API_KEY)
                parameter(HttpParam.PER_PAGE, Int.MAX_VALUE)
            }
            Resource.Success(data)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}