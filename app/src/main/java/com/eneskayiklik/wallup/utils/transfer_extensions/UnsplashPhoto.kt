package com.eneskayiklik.wallup.utils.transfer_extensions

import com.eneskayiklik.wallup.feature_home.data.dto.RelatedResult
import com.eneskayiklik.wallup.feature_home.data.dto.UnsplashPhotoDto
import com.eneskayiklik.wallup.feature_home.domain.model.*

fun UnsplashPhotoDto.toUIModel() = UnsplashPhoto(
    id = id ?: "",
    createdAt = created_at ?: "",
    blurHash = blur_hash ?: "",
    color = color ?: "",
    thumbnail = urls?.thumb ?: "",
    smallImage = urls?.small ?: "",
    fullImage = urls?.full ?: "",
    sourceUrl = links?.html ?: "",
    views = views ?: 0,
    downloads = downloads ?: 0,
    likes = likes ?: 0,
    userDetail = UserDetail(
        name = user?.name ?: "",
        unsplashProfile = user?.links?.html ?: "",
        image = user?.profile_image?.medium ?: "",
        instaUserName = user?.instagram_username ?: "",
        twitterUserName = user?.twitter_username ?: "",
    ),
    photoDetail = PhotoDetail(
        width = width ?: 0,
        height = height ?: 0,
        _cameraName = exif?.name ?: "",
        _aperture = exif?.aperture ?: "",
        _focalLength = exif?.focal_length ?: "",
        _exposureTime = exif?.exposure_time ?: "",
        _iso = exif?.iso?.toString() ?: ""
    ),
    relatedCollections = RelatedCollections(
        collections = related_collections?.results?.map { it.toUIModel() } ?: emptyList(),
        total = related_collections?.total ?: 0
    )
)

fun RelatedResult.toUIModel() = RelatedCollectionResult(
    id = id ?: "",
    title = title ?: "",
    color = cover_photo?.color ?: "",
    coverPhoto = cover_photo?.urls?.regular ?: ""
)