package com.grensil.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grensil.domain.dto.AnimeDto
import com.grensil.domain.usecase.GetAnimeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getAnimeListUseCase: GetAnimeListUseCase
) : ViewModel() {

    private val _bookmarkList = MutableStateFlow<List<AnimeDto>>(emptyList())
    val bookmarkList = _bookmarkList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        getBookmarkList()
    }

    private fun getBookmarkList() = viewModelScope.launch {
        try {
            _isLoading.value = true
            getAnimeListUseCase.getBookmarkList()
                .distinctUntilChanged()
                .collect {
                    _bookmarkList.value = it
                    _isLoading.value = false
                }
        } catch (_: Exception) {
            _isLoading.value = false
            _bookmarkList.value = emptyList()
        }
    }

    fun insertBookmark(animeDto: AnimeDto) = viewModelScope.launch {
        getAnimeListUseCase.addBookmark(animeDto)
    }

    fun deleteBookmark(animeId: Int) = viewModelScope.launch {
        getAnimeListUseCase.removeBookmark(animeId)
    }
}