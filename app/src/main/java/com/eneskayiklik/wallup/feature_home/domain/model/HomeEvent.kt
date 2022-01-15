package com.eneskayiklik.wallup.feature_home.domain.model

import com.ramcosta.composedestinations.spec.Direction

sealed class HomeEvent {
    data class Navigate(val route: Direction) : HomeEvent()
    object ScrollTop : HomeEvent()
}
