package com.grensil.data.datasource

import com.grensil.data.api.AnimeService
import com.grensil.data.model.AnimeListResponse
import javax.inject.Inject

class AnimeRemoteDataSourceImpl @Inject constructor(
    private val animeService: AnimeService
) : AnimeRemoteDataSource {

    override suspend fun getAnimeList(): AnimeListResponse {
        return animeService.getAnimeList()
    }
}