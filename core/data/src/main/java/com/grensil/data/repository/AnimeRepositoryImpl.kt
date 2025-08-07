package com.grensil.data.repository

import com.grensil.data.datasource.AnimeRemoteDataSource
import com.grensil.data.mapper.toAnimeEntity
import com.grensil.domain.entity.AnimeEntity
import com.grensil.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val localDataSource: AnimeRemoteDataSource,
    private val remoteDataSource: AnimeRemoteDataSource
) : AnimeRepository {

    override fun getAnimeList(keyword: String, offset: Int): Flow<List<AnimeEntity>> = flow {
        val response = remoteDataSource.getAnimeList().data?.map { it.toAnimeEntity() }
        emit(response ?: emptyList())
    }

    //TODO Local 가져오는 부분
}
