package com.eneskayiklik.wallup.feature_home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eneskayiklik.wallup.feature_home.domain.model.HomeEvent
import com.eneskayiklik.wallup.feature_home.domain.model.HomeState
import com.eneskayiklik.wallup.feature_home.domain.use_case.HomeUseCase
import com.eneskayiklik.wallup.utils.model.UiEvent
import com.eneskayiklik.wallup.utils.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: HomeUseCase
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent

    init {
        getHomeState()
    }

    fun onEvent(event: HomeEvent) {
        viewModelScope.launch {
            when (event) {
                is HomeEvent.Navigate -> viewModelScope.launch {
                    _uiEvent.emit(UiEvent.OnNavigate(event.route))
                }
                HomeEvent.ScrollTop -> viewModelScope.launch {
                    _uiEvent.emit(UiEvent.ScrollTop)
                }
            }
        }
    }

    private fun getHomeState() {
        getColors()
        getCategories()
        getRandomPhotos()
    }

    private fun getColors() {
        viewModelScope.launch {
            useCase.getColorList().collectLatest {
                _homeState.value = _homeState.value.copy(
                    colorList = it
                )
            }
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            useCase.getCategoryList().collectLatest {
                _homeState.value = _homeState.value.copy(
                    categories = it
                )
            }
        }
    }

    private fun getRandomPhotos() {
        viewModelScope.launch {
            useCase.getRandomPhotos().collectLatest {
                when (it) {
                    is Resource.Error -> Log.e("HomeViewModel", it.message)
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        _homeState.value = _homeState.value.copy(
                            randomPhotos = it.data
                        )
                    }
                }
            }
        }
    }
}