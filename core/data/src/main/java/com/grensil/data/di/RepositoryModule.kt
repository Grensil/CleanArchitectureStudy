package com.grensil.data.di

import com.grensil.data.repository.AnimeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsCharacterRepository(
        characterRepository: AnimeRepositoryImpl
    ): AnimeRepository
}