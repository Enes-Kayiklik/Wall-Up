package com.eneskayiklik.wallup.feature_collection.domain.use_case

import com.eneskayiklik.wallup.feature_collection.domain.repository.CollectionRepository
import com.eneskayiklik.wallup.utils.network.Resource
import com.eneskayiklik.wallup.utils.transfer_extensions.toUIModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CollectionUseCase @Inject constructor(
    private val repository: CollectionRepository
) {
    suspend fun getCollectionData(collectionId: String) = flow {
        when (val data = repository.getCollectionData(collectionId)) {
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