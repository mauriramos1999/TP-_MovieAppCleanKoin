package com.example.tp7_movieappcleankoin.domain.useCases

import com.example.tp7_movieappcleankoin.domain.repository.MoviesRepository
import com.example.tp7_movieappcleankoin.data.service.model.Movie
import com.example.tp7_movieappcleankoin.domain.util.CoroutineResult

class GetMoviesUseCase(private val moviesRepository: MoviesRepository) {

    suspend fun getMovies(): CoroutineResult<List<Movie>> {
        return when(val movies = moviesRepository.getMovies()){
            is CoroutineResult.Success -> {

                CoroutineResult.Success(movies.data)
            }
            is CoroutineResult.Failure ->{
                //CoroutineResult.Success(database.getAllMovies())
                CoroutineResult.Failure(movies.exception)
            }
        }
    }
}