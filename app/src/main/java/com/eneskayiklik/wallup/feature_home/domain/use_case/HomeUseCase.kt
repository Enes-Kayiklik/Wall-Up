package com.eneskayiklik.wallup.feature_home.domain.use_case

import com.eneskayiklik.wallup.feature_home.domain.repository.HomeRepository
import com.eneskayiklik.wallup.utils.network.Resource
import com.eneskayiklik.wallup.utils.transfer_extensions.toUIModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    suspend fun getRandomPhotos() = flow {
        emit(Resource.Loading())
        when (val data = repository.getRandomPhotos()) {
            is Resource.Error -> emit(Resource.Error(data.message))
            is Resource.Loading -> emit(Resource.Loading())
            is Resource.Success -> {
                try {
                    emit(Resource.Success(data.data.map { it.toUIModel() }))
                } catch (e: Exception) {
                    emit(Resource.Error("An unexpected error occurred"))
                }
            }
        }
    }

    suspend fun getColorList() = flow {
        emit(repository.getColorList().shuffled())
    }

    suspend fun getCategoryList() = flow {
        emit(repository.getCategoryList().shuffled())
    }
}