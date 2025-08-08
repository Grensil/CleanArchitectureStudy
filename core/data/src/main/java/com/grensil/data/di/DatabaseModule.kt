package com.grensil.data.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.grensil.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "clean_architecture"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideHistoryDAO(database: AppDatabase) = database.favoriteAnimeDao()
}