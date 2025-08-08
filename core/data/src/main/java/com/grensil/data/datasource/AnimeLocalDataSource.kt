package com.grensil.data.datasource

import com.grensil.data.local.entity.FavoriteAnimeEntity
import kotlinx.coroutines.flow.Flow

interface AnimeLocalDataSource {
    fun getBookmarkList(): Flow<List<FavoriteAnimeEntity>>
}