package com.grensil.domain.repository

import com.grensil.domain.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    fun getAnimeList(): Flow<List<AnimeEntity>>
}