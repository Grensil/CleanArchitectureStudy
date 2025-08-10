package com.grensil.data.datasource

import com.grensil.data.local.entity.FavoriteAnimeEntity
import com.grensil.domain.dto.AnimeDto
import kotlinx.coroutines.flow.Flow

interface AnimeLocalDataSource {
    fun getBookmarkList(): Flow<List<FavoriteAnimeEntity>>
    suspend fun removeAnimeList()
    suspend fun addBookmark(animeDto : FavoriteAnimeEntity)
    suspend fun removeBookmark(animeId : String)
}