package com.eneskayiklik.wallup.feature_collection.data.repository

import com.eneskayiklik.wallup.BuildConfig
import com.eneskayiklik.wallup.feature_collection.data.dto.SearchResponseDto
import com.eneskayiklik.wallup.feature_collection.domain.repository.SearchRepository
import com.eneskayiklik.wallup.feature_home.data.dto.UnsplashPhotoDto
import com.eneskayiklik.wallup.utils.network.HttpParam
import com.eneskayiklik.wallup.utils.network.HttpRoutes
import com.eneskayiklik.wallup.utils.network.Resource
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val client: HttpClient
): SearchRepository {

    override suspend fun getSearchData(query: String): Resource<List<UnsplashPhotoDto>> {
        return try {
            val data: SearchResponseDto = client.get {
                url(HttpRoutes.SEARCH)
                parameter(HttpParam.CLIENT_ID, BuildConfig.API_KEY)
                parameter(HttpParam.PER_PAGE, Int.MAX_VALUE)
                parameter(HttpParam.QUERY, query)
            }
            Resource.Success(data.results)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}