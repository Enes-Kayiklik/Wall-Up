package com.eneskayiklik.wallup.feature_detail.data.repository

import android.util.Log
import com.eneskayiklik.wallup.BuildConfig
import com.eneskayiklik.wallup.feature_bookmark.data.db.entity.BookmarkPhoto
import com.eneskayiklik.wallup.feature_bookmark.data.db.dao.BookmarkPhotoDao
import com.eneskayiklik.wallup.feature_detail.domain.repository.DetailRepository
import com.eneskayiklik.wallup.feature_home.data.dto.UnsplashPhotoDto
import com.eneskayiklik.wallup.utils.network.HttpParam
import com.eneskayiklik.wallup.utils.network.HttpRoutes
import com.eneskayiklik.wallup.utils.network.Resource
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val client: HttpClient,
    private val bookmarkDao: BookmarkPhotoDao
) : DetailRepository {

    override suspend fun getImageDetail(id: String): Resource<UnsplashPhotoDto> {
        return try {
            val data: UnsplashPhotoDto = client.get {
                url(HttpRoutes.PHOTO.plus("/$id"))
                parameter(HttpParam.CLIENT_ID, BuildConfig.API_KEY)
            }
            Resource.Success(data)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unexpected error occurred")
        }
    }

    override suspend fun isBookmarked(id: String): Boolean {
        return bookmarkDao.getSingleBookmark(id) != null
    }

    override suspend fun addBookmark(item: BookmarkPhoto) = bookmarkDao.addBookmark(item)

    override suspend fun removeBookmark(id: String) = bookmarkDao.removeBookmark(id)
}