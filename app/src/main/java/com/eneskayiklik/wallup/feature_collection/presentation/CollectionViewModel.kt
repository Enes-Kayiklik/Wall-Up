package com.eneskayiklik.wallup.feature_collection.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eneskayiklik.wallup.feature_collection.domain.use_case.CollectionUseCase
import com.eneskayiklik.wallup.feature_collection.domain.model.CollectionState
import com.eneskayiklik.wallup.feature_collection.domain.use_case.SearchUseCase
import com.eneskayiklik.wallup.utils.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val collectionUseCase: CollectionUseCase,
    args: SavedStateHandle
): ViewModel() {

    private val _collectionState = MutableStateFlow(CollectionState())
    val collectionState: StateFlow<CollectionState> = _collectionState

    init {
        checkArgumentsAndMakeRequest(
            args.get<String>("collectionId"),
            args.get<String>("searchQuery"),
            args.get<String>("title")
        )
    }

    private fun checkArgumentsAndMakeRequest(
        collectionId: String?,
        searchQuery: String?,
        title: String?
    ) {
        if (title != null || searchQuery != null) {
            _collectionState.value = _collectionState.value.copy(
                title = (title ?: searchQuery) ?: ""
            )
        }
        if (collectionId != null) {
            getCollectionResponse(collectionId)
        } else if (searchQuery != null) {
            getSearchResponse(searchQuery)
        }
    }

    private fun getSearchResponse(query: String) {
        viewModelScope.launch {
            searchUseCase.getSearchData(query).collectLatest {
                when (it) {
                    is Resource.Error -> Log.e("CollectionViewModel", it.message)
                    is Resource.Loading -> { }
                    is Resource.Success -> {
                        _collectionState.value = _collectionState.value.copy(
                            items = it.data,
                            count = it.data.count()
                        )
                    }
                }
            }
        }
    }

    private fun getCollectionResponse(collectionId: String) {
        viewModelScope.launch {
            collectionUseCase.getCollectionData(collectionId).collectLatest {
                when (it) {
                    is Resource.Error -> Log.e("CollectionViewModel", it.message)
                    is Resource.Loading -> { }
                    is Resource.Success -> {
                        _collectionState.value = _collectionState.value.copy(
                            items = it.data,
                            count = it.data.count()
                        )
                    }
                }
            }
        }
    }
}