package com.eneskayiklik.wallup.di

import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailModule {

    @Singleton
    @Provides
    fun provideDownloadManager(@ApplicationContext context: Context): DownloadManager =
        context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

    @Singleton
    @Provides
    fun provideWallpaperManager(@ApplicationContext context: Context): WallpaperManager =
        WallpaperManager.getInstance(context)
}