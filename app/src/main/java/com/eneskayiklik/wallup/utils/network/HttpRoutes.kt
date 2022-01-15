package com.eneskayiklik.wallup.utils.network

object HttpRoutes {
    private const val BASE_URL = "https://api.unsplash.com"
    const val RANDOM_PHOTO = "$BASE_URL/photos/random"
    const val PHOTO = "$BASE_URL/photos"
    const val COLLECTION = "$BASE_URL/collections/{id}/photos"
    const val SEARCH = "$BASE_URL/search/photos"
}

object HttpParam {
    const val CLIENT_ID = "client_id"
    const val COUNT = "count"
    const val PER_PAGE = "per_page"
    const val QUERY = "query"
}