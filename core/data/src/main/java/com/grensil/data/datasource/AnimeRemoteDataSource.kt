package com.grensil.data.datasource

import com.grensil.data.model.AnimeListResponse

interface AnimeRemoteDataSource {
    suspend fun getAnimeList(): AnimeListResponse
}