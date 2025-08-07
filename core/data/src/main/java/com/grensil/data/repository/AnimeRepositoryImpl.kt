package com.grensil.data.repository

import com.grensil.data.datasource.AnimeLocalDataSource
import com.grensil.data.datasource.AnimeRemoteDataSource
import com.grensil.data.mapper.toAnimeEntity
import com.grensil.domain.entity.AnimeEntity
import com.grensil.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeRepositoryImpl @Inject constructor(
    private val remoteDataSource: AnimeRemoteDataSource
) : AnimeRepository {

    override fun getAnimeList(): Flow<List<AnimeEntity>> = flow {
        val response = remoteDataSource.getAnimeList().data?.map { it.toAnimeEntity() }
        emit(response ?: emptyList())
    }
}
