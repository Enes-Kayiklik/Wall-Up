package com.eneskayiklik.wallup.feature_collection.domain.use_case

import com.eneskayiklik.wallup.feature_collection.domain.repository.SearchRepository
import com.eneskayiklik.wallup.utils.network.Resource
import com.eneskayiklik.wallup.utils.transfer_extensions.toUIModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    suspend fun getSearchData(query: String) = flow {
        when (val data = repository.getSearchData(query)) {
            is Resource.Error -> emit(Resource.Error(data.message))
            is Resource.Loading -> emit(Resource.Loading())
            is Resource.Success -> {
                try {
                    emit(Resource.Success(data.data.map { it.toUIModel() }))
                } catch (e: Exception) {
                    emit(Resource.Error(e.message ?: "An unexpected error occurred"))
                }
            }
        }
    }
}