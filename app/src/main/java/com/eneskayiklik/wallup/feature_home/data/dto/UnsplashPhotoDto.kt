package com.eneskayiklik.wallup.feature_home.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class UnsplashPhotoDto(
    val alt_description: String? = null,
    val blur_hash: String? = null,
    // val categories: List<@Contextual Any>,
    val color: String? = null,
    val created_at: String? = null,
    val related_collections: RelatedCollection? = null,
    val description: String? = null,
    val downloads: Int? = null,
    val height: Int? = null,
    val id: String? = null,
    val liked_by_user: Boolean? = null,
    val likes: Int? = null,
    val links: Links? = null,
    val location: Location? = null,
    val promoted_at: String? = null,
    val updated_at: String? = null,
    val urls: Urls? = null,
    val user: User? = null,
    val views: Int? = null,
    val width: Int? = null,
    val exif: Exif? = null
)

@Serializable
data class User(
    val accepted_tos: Boolean? = null,
    val bio: String? = null,
    val first_name: String? = null,
    val for_hire: Boolean? = null,
    val id: String? = null,
    val instagram_username: String? = null,
    val last_name: String? = null,
    val links: LinksX? = null,
    val location: String? = null,
    val name: String? = null,
    val portfolio_url: String? = null,
    val profile_image: ProfileImage? = null,
    val social: Social? = null,
    val total_collections: Int? = null,
    val total_likes: Int? = null,
    val total_photos: Int? = null,
    val twitter_username: String? = null,
    val updated_at: String? = null,
    val username: String? = null
)

@Serializable
data class Urls(
    val full: String? = null,
    val raw: String? = null,
    val regular: String? = null,
    val small: String? = null,
    val thumb: String? = null
)

@Serializable
data class Social(
    val instagram_username: String? = null,
    val paypal_email: String? = null,
    val portfolio_url: String? = null,
    val twitter_username: String? = null
)

@Serializable
data class ProfileImage(
    val large: String? = null,
    val medium: String? = null,
    val small: String? = null
)

@Serializable
data class Position(
    val latitude: Double? = null,
    val longitude: Double? = null
)

@Serializable
data class Location(
    val city: String? = null,
    val country: String? = null,
    val name: String? = null,
    val position: Position? = null,
    val title: String? = null
)

@Serializable
data class LinksX(
    val followers: String? = null,
    val following: String? = null,
    val html: String? = null,
    val likes: String? = null,
    val photos: String? = null,
    val portfolio: String? = null,
    val self: String? = null
)

@Serializable
data class Links(
    val download: String? = null,
    val download_location: String? = null,
    val html: String? = null,
    val self: String? = null
)

@Serializable
data class RelatedCollection(
    val results: List<RelatedResult>? = null,
    val total: Int? = null,
    val type: String? = null
)

@Serializable
data class RelatedResult(
    val id: String? = null,
    val title: String? = null,
    val total_photos: Int? = null,
    val cover_photo: CoverPhoto? = null,
)

@Serializable
data class CoverPhoto(
    val blur_hash: String? = null,
    val color: String? = null,
    val urls: Urls? = null,
)

@Serializable
data class Exif(
    val aperture: String? = null,
    val exposure_time: String? = null,
    val focal_length: String? = null,
    val iso: Int? = null,
    val make: String? = null,
    val model: String? = null,
    val name: String? = null
)