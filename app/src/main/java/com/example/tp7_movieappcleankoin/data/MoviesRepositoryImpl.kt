package com.example.tp7_movieappcleankoin.data

import com.example.tp7_movieappcleankoin.data.database.MovieDatabase
import com.example.tp7_movieappcleankoin.domain.repository.MoviesRepository
import com.example.tp7_movieappcleankoin.data.service.MovieClientPopular
import com.example.tp7_movieappcleankoin.data.service.model.Movie
import com.example.tp7_movieappcleankoin.domain.util.CoroutineResult

class MoviesRepositoryImpl(private val movieClient: MovieClientPopular,
                           private val database: MovieDatabase
) : MoviesRepository {

    override suspend fun getMovies(): CoroutineResult<List<Movie>> {
        try {
            val response = movieClient.getMovies().execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    database.insertMovies(it.movies)
                    return CoroutineResult.Success(database.getAllMovies())
                }
            }
            return CoroutineResult.Failure(Exception(response.errorBody().toString()))
        }
        catch(e: Exception){
            return CoroutineResult.Failure(e)
        }
    }
}