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
//    operator fun invoke() {
//        userPrefRepository.getAnimeList()
//    }

    fun getAnimeList() : Flow<List<AnimeDto>> {
        //return flowOf(emptyList())
        return localAnimeRepository.getAnimeList()
    }
}