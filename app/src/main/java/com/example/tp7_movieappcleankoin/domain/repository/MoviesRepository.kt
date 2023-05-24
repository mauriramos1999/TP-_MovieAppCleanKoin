package com.example.tp7_movieappcleankoin.domain.repository

import com.example.tp7_movieappcleankoin.data.service.model.Movie
import com.example.tp7_movieappcleankoin.domain.util.CoroutineResult

interface MoviesRepository {

    suspend fun getMovies(): CoroutineResult<List<Movie>>
}