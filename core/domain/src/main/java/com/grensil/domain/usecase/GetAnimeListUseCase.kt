package com.grensil.domain.usecase

import com.grensil.domain.dto.AnimeDto
import com.grensil.domain.repository.AnimeRepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAnimeListUseCase @Inject constructor(
    private val animeRepository: AnimeRepository
) {

    suspend fun getAnimeList() : Flow<List<AnimeDto>> {
        return animeRepository.getAnimeList()
    }

    suspend fun getBookmarkList() : Flow<List<AnimeDto>> {
        return animeRepository.getBookmarkList()
    }

    suspend fun addBookmark(animeDto: AnimeDto) {
        animeRepository.addBookmark(animeDto)
    }

    suspend fun removeBookmark(animeId: Int) {
        animeRepository.removeBookmark(animeId)
    }

    suspend fun removeAllBookmarkList() {
        animeRepository.removeAnimeList()
    }

    suspend fun searchAnimeList(query: String): Flow<List<AnimeDto>> {
        return animeRepository.getAnimeList().map { bookmarkList ->
            if (query.isEmpty()) {
                bookmarkList
            } else {
                bookmarkList.filter { anime ->
                    anime.anime_name.contains(query, ignoreCase = true)
                }
            }
        }
    }
}