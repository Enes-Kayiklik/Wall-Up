package com.eneskayiklik.wallup.feature_home.domain.model

data class UnsplashPhoto(
    val id: String,
    val createdAt: String,
    val blurHash: String,
    val color: String,
    val thumbnail: String,
    val smallImage: String,
    val fullImage: String,
    val views: Int,
    val downloads: Int,
    val likes: Int,
    val sourceUrl: String,
    val photoDetail: PhotoDetail,
    val userDetail: UserDetail,
    val relatedCollections: RelatedCollections,
    val isBookmarked: Boolean = false
)

data class UserDetail(
    val name: String,
    val image: String,
    val instaUserName: String,
    val twitterUserName: String,
    val unsplashProfile: String,
)

data class PhotoDetail(
    val width: Int,
    val height: Int,
    private val _cameraName: String,
    private val _aperture: String,
    private val _focalLength: String,
    private val _exposureTime: String,
    private val _iso: String
) {
    val resolution = if (width != 0 && height != 0) "${width}x$height" else "Unknown"
    val camera = if (_cameraName.isNotEmpty()) _cameraName else "Unknown"
    val aperture = if (_aperture.isNotEmpty()) _aperture else "Unknown"
    val focalLength = if (_focalLength.isNotEmpty()) _focalLength else "Unknown"
    val exposureTime = if (_exposureTime.isNotEmpty()) _exposureTime else "Unknown"
    val iso = if (_iso.isNotEmpty()) _iso else "Unknown"
}

data class RelatedCollections(
    val collections: List<RelatedCollectionResult>,
    val total: Int
)

data class RelatedCollectionResult(
    val id: String,
    val title: String,
    val color: String,
    val coverPhoto: String
)
