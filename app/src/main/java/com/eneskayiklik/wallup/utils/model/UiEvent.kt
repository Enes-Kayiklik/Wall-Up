package com.eneskayiklik.wallup.utils.model

import com.ramcosta.composedestinations.spec.Direction

sealed class UiEvent {
    data class OnNavigate(val route: Direction) : UiEvent()
    object PopBack : UiEvent()
    object ScrollTop : UiEvent()
    data class ShowToast(val title: String) : UiEvent()
}
