package com.grensil.domain.repository

import com.grensil.domain.dto.AnimeDto
import com.grensil.domain.dto.FavoriteAnimeDto
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    suspend fun getAnimeList(): Flow<List<AnimeDto>>
    suspend fun getBookmarkList() : Flow<List<AnimeDto>>
    suspend fun removeAnimeList()
    suspend fun addBookmark(animeDto : AnimeDto)
    suspend fun removeBookmark(animeId : Int)
}