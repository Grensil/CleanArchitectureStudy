package com.grensil.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_anime")
data class FavoriteAnimeEntity(
    @PrimaryKey(autoGenerate = true)  @ColumnInfo(name = "id" )val id: Int = 0,
    @ColumnInfo(name = "animeId") val animeId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "bookmarked") val bookmarked : Boolean,
    @ColumnInfo(name = "anime_img") val anime_img : String,
    @ColumnInfo(name = "addedAt") val addedAt: Long = System.currentTimeMillis()
)