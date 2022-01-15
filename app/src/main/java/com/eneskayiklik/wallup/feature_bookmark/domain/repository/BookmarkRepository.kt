package com.eneskayiklik.wallup.feature_bookmark.domain.repository

import com.eneskayiklik.wallup.feature_bookmark.data.db.entity.BookmarkPhoto

interface BookmarkRepository {

    suspend fun getAllBookmarks(): List<BookmarkPhoto>
}