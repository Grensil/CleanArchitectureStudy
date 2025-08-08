package com.grensil.data.local.database

import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.grensil.data.local.dao.FavoriteAnimeDao
import com.grensil.data.local.entity.FavoriteAnimeEntity


@androidx.room.Database(entities = [FavoriteAnimeEntity::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteAnimeDao(): FavoriteAnimeDao
}

