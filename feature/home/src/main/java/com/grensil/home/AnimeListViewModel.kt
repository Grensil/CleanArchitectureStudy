package com.grensil.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

import androidx.lifecycle.viewModelScope

import com.grensil.domain.entity.AnimeEntity
import com.grensil.domain.usecase.GetAnimeListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Singleton


@HiltViewModel
class AnimeListViewModel @Inject constructor(
    private val getAnimeListUseCase: GetAnimeListUseCase
) : ViewModel() {

    private val _animeList = MutableStateFlow<List<AnimeEntity>>(emptyList())
    val animeList = _animeList.asStateFlow()

    fun getAnimeList() = viewModelScope.launch {
        getAnimeListUseCase.getAnimeList()
            .distinctUntilChanged()
            .collect {
                _animeList.value = it
            }
    }
}