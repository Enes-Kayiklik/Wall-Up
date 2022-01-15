package com.eneskayiklik.wallup.feature_bookmark.domain.use_case

import com.eneskayiklik.wallup.feature_bookmark.domain.repository.BookmarkRepository
import com.eneskayiklik.wallup.utils.network.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookmarkUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {
    suspend fun getAllBookmarks() = repository.getAllBookmarks()
}