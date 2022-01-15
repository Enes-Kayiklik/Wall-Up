package com.eneskayiklik.wallup.feature_detail.presentation

import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eneskayiklik.wallup.destinations.CollectionScreenDestination
import com.eneskayiklik.wallup.feature_bookmark.data.db.entity.BookmarkPhoto
import com.eneskayiklik.wallup.feature_detail.domain.model.DetailEvent
import com.eneskayiklik.wallup.feature_detail.domain.model.DetailState
import com.eneskayiklik.wallup.feature_detail.domain.model.DownloadType
import com.eneskayiklik.wallup.feature_detail.domain.model.ScreenType.*
import com.eneskayiklik.wallup.feature_detail.domain.use_case.DetailUseCase
import com.eneskayiklik.wallup.utils.extensions.getImageUri
import com.eneskayiklik.wallup.utils.model.UiEvent
import com.eneskayiklik.wallup.utils.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: DetailUseCase,
    private val downloadManager: DownloadManager,
    private val wallpaperManager: WallpaperManager,
    args: SavedStateHandle
) : ViewModel() {
    private val _detailState = MutableStateFlow(DetailState())
    val detailState: StateFlow<DetailState> = _detailState

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent

    private var _downloadType = DownloadType.NONE
    var mDownloadId: Long? = null

    init {
        getPhotoDetail(
            args.get<String>("id"),
            args.get<String>("thumbnail")
        )
    }

    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    fun onEvent(event: DetailEvent) {
        viewModelScope.launch {
            when (event) {
                is DetailEvent.OnBookmarkClick -> {
                    updateBookmarkState(event.id, event.thumbnail, event.color, event.addBookmark)
                }
                is DetailEvent.OnDownloadClick -> startDownload(event.url, event.createdAt)
                is DetailEvent.OnWallpaper -> event.context.setAsWallpaper(event.bitmap)
                is DetailEvent.Navigate -> {
                    viewModelScope.launch {
                        _uiEvent.emit(
                            if (event.id == null) UiEvent.PopBack
                            else UiEvent.OnNavigate(CollectionScreenDestination(title = event.title, collectionId = event.id))
                        )
                    }
                }
                is DetailEvent.UpdateDrawable -> {
                    _detailState.value = _detailState.value.copy(
                        imageDrawable = event.drawable
                    )
                }
                is DetailEvent.Share -> event.context.shareImage(event.drawable)
                is DetailEvent.ShareText -> event.context.shareText(event.data)
            }
        }
    }

    private fun updateBookmarkState(
        id: String,
        thumbnail: String,
        color: String,
        addBookmark: Boolean
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _detailState.value = _detailState.value.copy(
                imageDetail = _detailState.value.imageDetail?.copy(
                    isBookmarked = addBookmark
                )
            )
            if (addBookmark) {
                useCase.addBookmark(
                    BookmarkPhoto(
                        unsplashId = id,
                        thumbnail = thumbnail,
                        color = color
                    )
                )
            } else {
                useCase.removeBookmark(id)
            }
        }
    }

    private fun getPhotoDetail(id: String?, thumbnail: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (thumbnail.isNullOrEmpty().not()) {
                val decodedUrl = URLDecoder.decode(thumbnail, StandardCharsets.UTF_8.toString())
                _detailState.value = _detailState.value.copy(thumbnail = decodedUrl)
            }

            // make api request for getting detail of selected photo
            if (id == null) {
                // TODO("error state")
                return@launch
            }
            useCase.getPhotoDetail(id).collectLatest {
                when (it) {
                    is Resource.Error -> Log.e("DetailViewModel", it.message)
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        _detailState.value = _detailState.value.copy(
                            imageDetail = it.data
                        )
                    }
                }
            }
        }
    }

    private fun startDownload(url: String, createdAt: String) {
        if (url.isNotEmpty()) {
            val req = DownloadManager.Request(
                Uri.parse(url)
            ).setTitle(createdAt)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setAllowedOverMetered(true)
            val downloadId = downloadManager.enqueue(req)
            viewModelScope.launch {
                _detailState.value = _detailState.value.copy(
                    currentDownloadId = downloadId
                )
                mDownloadId = downloadId
                _uiEvent.emit(UiEvent.ShowToast("Download started"))
            }
        }
    }

    fun downloadComplete() {
        viewModelScope.launch {
            if (mDownloadId != null) {
                val uri = downloadManager.getUriForDownloadedFile(mDownloadId!!)
                mDownloadId = null
                _detailState.value = _detailState.value.copy(
                    currentDownloadId = null
                )
                _uiEvent.emit(UiEvent.ShowToast("Download complete"))

                when (_downloadType) {
                    DownloadType.SHARE -> {

                    }
                    DownloadType.NONE -> {
                        return@launch
                    }
                }
                _downloadType = DownloadType.NONE
            }
        }
    }

    private fun Context.setAsWallpaper(drawable: Drawable?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val bitmap = (drawable as? BitmapDrawable)?.bitmap
                startActivity(wallpaperManager.getCropAndSetWallpaperIntent(getImageUri(bitmap)))
            } catch (e: Exception) {
                e.printStackTrace()
                _uiEvent.emit(UiEvent.ShowToast("Unsuccessful"))
            }
        }
    }

    private fun Context.shareImage(drawable: Drawable?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val uri = getImageUri((drawable as? BitmapDrawable)?.bitmap)
                Intent(Intent.ACTION_SEND).apply {
                    type = "image/jpeg"
                    putExtra(Intent.EXTRA_STREAM, uri)
                    startActivity(Intent.createChooser(this, "Share via"))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _uiEvent.emit(UiEvent.ShowToast("Try again after the photo is uploaded"))
            }
        }
    }

    private fun Context.shareText(data: String) {
        Intent(Intent.ACTION_VIEW).apply {
            setData(Uri.parse(data))
            startActivity(Intent.createChooser(this, "View via"))
        }
    }
}