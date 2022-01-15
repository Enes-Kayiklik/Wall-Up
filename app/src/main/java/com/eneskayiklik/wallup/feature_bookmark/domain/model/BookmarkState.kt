package com.eneskayiklik.wallup.feature_bookmark.domain.model

import com.eneskayiklik.wallup.feature_bookmark.data.db.entity.BookmarkPhoto

data class BookmarkState(
    val title: String = "Bookmarked Items",
    val count: Int = 0,
    val items: List<BookmarkPhoto> = emptyList()
)
