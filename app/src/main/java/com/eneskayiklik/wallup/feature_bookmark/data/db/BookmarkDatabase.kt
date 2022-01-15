package com.eneskayiklik.wallup.feature_bookmark.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eneskayiklik.wallup.feature_bookmark.data.db.entity.BookmarkPhoto
import com.eneskayiklik.wallup.feature_bookmark.data.db.dao.BookmarkPhotoDao

@Database(entities = [BookmarkPhoto::class], version = 1)
abstract class BookmarkDatabase: RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkPhotoDao
}