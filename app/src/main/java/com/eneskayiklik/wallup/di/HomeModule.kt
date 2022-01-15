package com.eneskayiklik.wallup.di

import android.content.Context
import androidx.room.Room
import com.eneskayiklik.wallup.feature_collection.data.repository.CollectionRepositoryImpl
import com.eneskayiklik.wallup.feature_bookmark.data.repository.BookmarkRepositoryImpl
import com.eneskayiklik.wallup.feature_collection.domain.repository.CollectionRepository
import com.eneskayiklik.wallup.feature_bookmark.domain.repository.BookmarkRepository
import com.eneskayiklik.wallup.feature_collection.domain.use_case.CollectionUseCase
import com.eneskayiklik.wallup.feature_bookmark.domain.use_case.BookmarkUseCase
import com.eneskayiklik.wallup.feature_collection.data.repository.SearchRepositoryImpl
import com.eneskayiklik.wallup.feature_collection.domain.repository.SearchRepository
import com.eneskayiklik.wallup.feature_collection.domain.use_case.SearchUseCase
import com.eneskayiklik.wallup.feature_bookmark.data.db.BookmarkDatabase
import com.eneskayiklik.wallup.feature_detail.data.repository.DetailRepositoryImpl
import com.eneskayiklik.wallup.feature_bookmark.data.db.dao.BookmarkPhotoDao
import com.eneskayiklik.wallup.feature_detail.domain.repository.DetailRepository
import com.eneskayiklik.wallup.feature_detail.domain.use_case.DetailUseCase
import com.eneskayiklik.wallup.feature_home.data.repository.HomeRepositoryImpl
import com.eneskayiklik.wallup.feature_home.domain.repository.HomeRepository
import com.eneskayiklik.wallup.feature_home.domain.use_case.HomeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHttpClient() = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                coerceInputValues = true
                ignoreUnknownKeys = true
            })
        }
    }

    @Singleton
    @Provides
    fun provideRoomDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, BookmarkDatabase::class.java, "bookmark").build()

    @Singleton
    @Provides
    fun provideBookmarkDao(db: BookmarkDatabase): BookmarkPhotoDao =
        db.bookmarkDao()

    @Singleton
    @Provides
    fun provideHomeRepository(
        client: HttpClient
    ): HomeRepository = HomeRepositoryImpl(client)

    @Singleton
    @Provides
    fun provideHomeUseCase(
        repository: HomeRepository
    ): HomeUseCase = HomeUseCase(repository)

    @Singleton
    @Provides
    fun provideDetailRepository(
        client: HttpClient,
        dao: BookmarkPhotoDao
    ): DetailRepository = DetailRepositoryImpl(client, dao)

    @Singleton
    @Provides
    fun provideDetailUseCase(
        repository: DetailRepository
    ): DetailUseCase = DetailUseCase(repository)

    @Singleton
    @Provides
    fun provideCollectionRepository(
        client: HttpClient
    ): CollectionRepository = CollectionRepositoryImpl(client)

    @Singleton
    @Provides
    fun provideCollectionUseCase(
        repository: CollectionRepository
    ): CollectionUseCase = CollectionUseCase(repository)

    @Singleton
    @Provides
    fun provideSearchRepository(
        client: HttpClient
    ): SearchRepository = SearchRepositoryImpl(client)

    @Singleton
    @Provides
    fun provideSearchUseCase(
        repository: SearchRepository
    ): SearchUseCase = SearchUseCase(repository)

    @Singleton
    @Provides
    fun provideBookmarkRepository(
        dao: BookmarkPhotoDao
    ): BookmarkRepository = BookmarkRepositoryImpl(dao)

    @Singleton
    @Provides
    fun provideBookmarkUseCase(
        repository: BookmarkRepository
    ): BookmarkUseCase = BookmarkUseCase(repository)
}