package com.grensil.data.datasource

import com.grensil.data.model.remote.AnimeItem
import com.grensil.data.model.remote.AnimeListResponse
import kotlinx.coroutines.flow.Flow

interface AnimeRemoteDataSource {
    fun getAnimeList(): Flow<List<AnimeItem>>
}