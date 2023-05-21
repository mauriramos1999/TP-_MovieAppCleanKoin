package com.example.tp7_movieappcleankoin.data.database.mapper

import com.example.tp7_movieappcleankoin.data.database.entity.MovieEntity
import com.example.tp7_movieappcleankoin.data.service.model.Movie

fun Movie.mapToDataBaseMovie(): MovieEntity =
    MovieEntity(
        id = id,
        adult = adult,
        backdrop_path = backdrop_path,
        original_language = original_language,
        original_title = original_title,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        release_date = release_date,
        title = title,
        video = video,
        vote_average = vote_average,
        vote_count = vote_count
    )

fun List<MovieEntity>.mapToLocalMovie(): List<Movie> =
    map { entity ->
        Movie(
            id = entity.id,
            adult = entity.adult,
            backdrop_path = entity.backdrop_path,
            original_language = entity.original_language,
            original_title = entity.original_title,
            overview = entity.overview,
            popularity = entity.popularity,
            poster_path = entity.poster_path,
            release_date = entity.release_date,
            title = entity.title,
            video = entity.video,
            vote_average = entity.vote_average,
            vote_count = entity.vote_count
        )
    }