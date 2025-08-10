package com.grensil.data.local.dao


import com.grensil.domain.dto.FavoriteAnimeDto
import kotlinx.coroutines.flow.Flow

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.grensil.data.local.entity.FavoriteAnimeEntity

@Dao
interface FavoriteAnimeDao {

    @Query("SELECT * FROM favorite_anime ORDER BY addedAt DESC")
    fun getAllFavorites(): Flow<List<FavoriteAnimeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: FavoriteAnimeEntity)

    @Delete
    suspend fun deleteBookmark(bookmark: FavoriteAnimeEntity)
}