package com.grensil.data.datasource

import com.grensil.data.model.AnimeItem
import kotlinx.coroutines.flow.Flow

interface AnimeRemoteDataSource {
    fun getAnimeList(): Flow<List<AnimeItem>>
}