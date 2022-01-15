package com.eneskayiklik.wallup.feature_bookmark.data.db.dao

import androidx.room.*
import com.eneskayiklik.wallup.feature_bookmark.data.db.entity.BookmarkPhoto

@Dao
interface BookmarkPhotoDao {

    @Query("SELECT * FROM bookmark WHERE unsplash_id = :id")
    fun getSingleBookmark(id: String): BookmarkPhoto?

    @Query("SELECT * FROM bookmark")
    fun getAllBookmarks(): List<BookmarkPhoto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBookmark(data: BookmarkPhoto)

    @Query("DELETE FROM bookmark WHERE unsplash_id = :id")
    fun removeBookmark(id: String)
}