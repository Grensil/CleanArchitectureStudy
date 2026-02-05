package com.grensil.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel

import com.grensil.domain.dto.AnimeDto
import com.grensil.domain.usecase.GetAnimeListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


@HiltViewModel
class AnimeListViewModel @Inject constructor(
    private val getAnimeListUseCase: GetAnimeListUseCase
) : ViewModel() {

    private val _animeList = MutableStateFlow<List<AnimeDto>>(emptyList())
    val animeList = _animeList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        getAnimeList()
    }

    private fun getAnimeList() = viewModelScope.launch {
        try {
            _isLoading.value = true
            getAnimeListUseCase.getAnimeList()
                .distinctUntilChanged()
                .collect {
                    _animeList.value = it
                    _isLoading.value = false
                }
        } catch (_: Exception) {
            _isLoading.value = false
            _animeList.value = emptyList()
        }
    }

    fun insertBookmark(animeDto: AnimeDto) = viewModelScope.launch {
        getAnimeListUseCase.addBookmark(animeDto)
    }

    fun deleteBookmark(animeId: Int) = viewModelScope.launch {
        getAnimeListUseCase.removeBookmark(animeId)
    }
}