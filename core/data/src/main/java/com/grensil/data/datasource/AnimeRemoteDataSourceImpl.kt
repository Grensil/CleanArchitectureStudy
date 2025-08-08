package com.grensil.data.datasource

import com.grensil.data.api.AnimeService
import com.grensil.data.model.remote.AnimeItem
import com.grensil.data.model.remote.AnimeListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeRemoteDataSourceImpl @Inject constructor(
    private val animeService: AnimeService
) : AnimeRemoteDataSource {

    override fun getAnimeList(): Flow<List<AnimeItem>> = flow {
        val response = animeService.getAnimeList()
        emit(response.data)
    }
}