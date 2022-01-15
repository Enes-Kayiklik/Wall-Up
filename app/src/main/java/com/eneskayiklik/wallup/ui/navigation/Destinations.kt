package com.eneskayiklik.wallup.ui.navigation

sealed class Destinations(val route: String) {
    object Splash : Destinations("splash")
    object Home : Destinations("home")
    object Detail : Destinations("detail")
    object DetailWithArgs : Destinations("detail/{id}?thumbnail={thumbnail}")
    object Collection : Destinations("collection")
    object CollectionWithArgs :
        Destinations("collection?title={title}&collectionId={collectionId}&searchQuery={searchQuery}")
    object Bookmark : Destinations("bookmark")
}
