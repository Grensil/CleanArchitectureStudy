package com.grensil.domain.usecase

import com.grensil.domain.dto.AnimeDto
import com.grensil.domain.repository.AnimeRepository

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAnimeListUseCase @Inject constructor(
    private val localAnimeRepository: AnimeRepository
) {

    suspend fun getAnimeList() : Flow<List<AnimeDto>> {
        return localAnimeRepository.getAnimeList()
    }

    suspend fun getBookmarkList() : Flow<List<AnimeDto>> {
        return localAnimeRepository.getBookmarkList()
    }

    suspend fun addBookmark(animeDto: AnimeDto) {
        localAnimeRepository.addBookmark(animeDto)
    }

    suspend fun removeBookmark(animeId: Int) {
        localAnimeRepository.removeBookmark(animeId)
    }

    suspend fun removeAllBookmarkList() {
        localAnimeRepository.removeAnimeList()
    }
}