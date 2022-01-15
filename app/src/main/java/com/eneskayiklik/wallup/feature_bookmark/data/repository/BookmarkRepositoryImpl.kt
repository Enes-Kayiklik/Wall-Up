package com.eneskayiklik.wallup.feature_bookmark.data.repository

import com.eneskayiklik.wallup.feature_bookmark.domain.repository.BookmarkRepository
import com.eneskayiklik.wallup.feature_bookmark.data.db.entity.BookmarkPhoto
import com.eneskayiklik.wallup.feature_bookmark.data.db.dao.BookmarkPhotoDao
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val dao: BookmarkPhotoDao
): BookmarkRepository {

    override suspend fun getAllBookmarks(): List<BookmarkPhoto> {
        return dao.getAllBookmarks()
    }
}