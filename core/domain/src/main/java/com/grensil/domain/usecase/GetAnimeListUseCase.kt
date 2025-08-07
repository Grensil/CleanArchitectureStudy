package com.grensil.domain.usecase

import com.grensil.domain.entity.AnimeEntity
import com.grensil.domain.repository.AnimeRepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAnimeListUseCase @Inject constructor(
    private val userPrefRepository: AnimeRepository
) {
//    operator fun invoke() {
//        userPrefRepository.getAnimeList()
//    }

    fun getAnimeList() : Flow<List<AnimeEntity>> {
        //return flowOf(emptyList())
        return userPrefRepository.getAnimeList()
    }
}