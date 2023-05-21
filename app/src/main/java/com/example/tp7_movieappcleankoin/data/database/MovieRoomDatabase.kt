package com.example.tp7_movieappcleankoin.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tp7_movieappcleankoin.data.database.dao.MovieDAO
import com.example.tp7_movieappcleankoin.data.database.entity.MovieEntity


@Database(entities = [
    MovieEntity::class,
],
    version = 1,
)
abstract class MovieRoomDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDAO
}