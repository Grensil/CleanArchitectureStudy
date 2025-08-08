package com.grensil.domain.repository

import com.grensil.domain.dto.AnimeDto
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    fun getAnimeList(): Flow<List<AnimeDto>>
}