package com.grensil.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grensil.domain.dto.AnimeDto
import com.grensil.domain.usecase.GetAnimeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getAnimeListUseCase: GetAnimeListUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _searchList = MutableStateFlow<List<AnimeDto>>(emptyList())
    val searchList = _searchList.asStateFlow()

    init {
        searchQuery
            .debounce(300)
            .distinctUntilChanged()
            .onEach { query ->
                searchAnimeList(query)
            }
            .launchIn(viewModelScope)
    }

    fun searchAnimeList(search: String) = viewModelScope.launch {
        getAnimeListUseCase.searchAnimeList(search)
            .distinctUntilChanged()
            .collect {
                if (search == searchQuery.value) {
                    _searchList.value = it
                }
            }
    }

    fun updateSearchQuery(query: String) {
        savedStateHandle["search_query"] = query
        _searchQuery.value = query
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