package com.grensil.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grensil.domain.dto.AnimeDto
import com.grensil.domain.usecase.GetAnimeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getAnimeListUseCase: GetAnimeListUseCase
) : ViewModel() {

    private val _animeDetail = MutableStateFlow<AnimeDto?>(null)
    val animeDetail = _animeDetail.asStateFlow()

    fun setAnimeDetail(anime: AnimeDto) {
        _animeDetail.value = anime
    }


    fun insertBookmark(animeDto: AnimeDto) = viewModelScope.launch {
        getAnimeListUseCase.addBookmark(animeDto)
    }

    fun deleteBookmark(animeId: Int) = viewModelScope.launch {
        getAnimeListUseCase.removeBookmark(animeId)
    }
}