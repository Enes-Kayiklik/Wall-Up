package com.eneskayiklik.wallup.feature_detail.domain.repository

import com.eneskayiklik.wallup.feature_bookmark.data.db.entity.BookmarkPhoto
import com.eneskayiklik.wallup.feature_home.data.dto.UnsplashPhotoDto
import com.eneskayiklik.wallup.utils.network.Resource

interface DetailRepository {

    suspend fun getImageDetail(id: String): Resource<UnsplashPhotoDto>

    suspend fun isBookmarked(id: String): Boolean

    suspend fun addBookmark(item: BookmarkPhoto)

    suspend fun removeBookmark(id: String)
}