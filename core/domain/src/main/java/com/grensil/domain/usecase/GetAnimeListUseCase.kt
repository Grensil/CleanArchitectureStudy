package com.grensil.domain.usecase

import com.grensil.domain.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetAnimeListUseCase @Inject constructor(
    private val userPrefRepository: AnimeRepository
) {
    operator fun invoke() {
        userPrefRepository.getAnimeList()
    }
}