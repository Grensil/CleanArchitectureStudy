package com.grensil.data.datasource

import com.grensil.data.model.AnimeEntity
import kotlinx.coroutines.flow.Flow

interface AnimeRemoteDataSource {
    fun getAnimeList(): Flow<List<AnimeEntity>>
}