package com.example.tp7_movieappcleankoin.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
class MovieEntity (
    @PrimaryKey var id: Int,
    var adult: Boolean,
    var backdrop_path: String,
    var original_language: String,
    var original_title: String,
    var overview: String,
    var popularity: Double,
    var poster_path: String,
    var release_date: String,
    var title: String,
    var video: Boolean,
    var vote_average: Double,
    var vote_count: Int
)