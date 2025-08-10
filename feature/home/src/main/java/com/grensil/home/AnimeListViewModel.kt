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

    private val _bookmarkList = MutableStateFlow<List<AnimeDto>>(emptyList())
    val bookmarkList = _bookmarkList.asStateFlow()

    fun getAnimeList() = viewModelScope.launch {
        getAnimeListUseCase.getAnimeList()
            .distinctUntilChanged()
            .collect {
                _animeList.value = it
            }
    }

    fun getBookmarkList() = viewModelScope.launch {
        getAnimeListUseCase.getBookmarkList()
            .distinctUntilChanged()
            .collect {
                _bookmarkList.value = it
            }
    }

    fun insertBookmark(animeDto: AnimeDto) = viewModelScope.launch {
        getAnimeListUseCase.addBookmark(animeDto)
    }

    fun deleteBookmark(animeId: Int) = viewModelScope.launch {
        getAnimeListUseCase.removeBookmark(animeId)
    }

    fun deleteAllBookmark() = viewModelScope.launch {
        getAnimeListUseCase.removeAllBookmarkList()
    }
}