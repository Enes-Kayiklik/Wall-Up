package com.eneskayiklik.wallup.feature_bookmark.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eneskayiklik.wallup.feature_bookmark.domain.model.BookmarkState
import com.eneskayiklik.wallup.feature_bookmark.domain.use_case.BookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val bookmarkUseCase: BookmarkUseCase
): ViewModel() {

    private val _bookmarkState = MutableStateFlow(BookmarkState())
    val bookmarkState: StateFlow<BookmarkState> = _bookmarkState

    init {
        getBookmarks()
    }

    private fun getBookmarks() {
        viewModelScope.launch(Dispatchers.IO) {
            val items = bookmarkUseCase.getAllBookmarks()
            _bookmarkState.value = _bookmarkState.value.copy(
                count = items.count(),
                items = items
            )
        }
    }
}