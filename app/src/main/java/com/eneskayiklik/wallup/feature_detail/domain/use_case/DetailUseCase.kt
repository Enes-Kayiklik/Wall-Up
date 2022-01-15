package com.eneskayiklik.wallup.feature_detail.domain.use_case

import com.eneskayiklik.wallup.feature_bookmark.data.db.entity.BookmarkPhoto
import com.eneskayiklik.wallup.feature_detail.domain.repository.DetailRepository
import com.eneskayiklik.wallup.utils.network.Resource
import com.eneskayiklik.wallup.utils.transfer_extensions.toUIModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailUseCase @Inject constructor(
    private val repository: DetailRepository
) {

    suspend fun getPhotoDetail(id: String) = flow {
        emit(Resource.Loading())
        when (val data = repository.getImageDetail(id)) {
            is Resource.Error -> emit(Resource.Error(data.message))
            is Resource.Loading -> emit(Resource.Loading())
            is Resource.Success -> {
                try {
                    emit(Resource.Success(data.data.toUIModel().copy(isBookmarked = isBookmarked(id))))
                } catch (e: Exception) {
                    emit(Resource.Error(e.message ?: "An unexpected error occurred"))
                }
            }
        }
    }

    suspend fun addBookmark(item: BookmarkPhoto) {
        repository.addBookmark(item)
    }

    suspend fun removeBookmark(id: String) {
        repository.removeBookmark(id)
    }

    private suspend fun isBookmarked(id: String) = repository.isBookmarked(id)
}