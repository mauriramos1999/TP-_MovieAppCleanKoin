package com.example.tp7_movieappcleankoin.service

import com.example.tp7_movieappcleankoin.service.model.MovieList
import com.example.tp7_movieappcleankoin.util.CoroutineResult

class MovieService( private val movieClient: MovieClient) {

    suspend fun getMovies(): CoroutineResult<MovieList>{
        try {
            val response = movieClient.getMovies().execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    return CoroutineResult.Success(it)
                }
            }
            return CoroutineResult.Failure(Exception(response.errorBody().toString()))
        }
        catch(e: Exception){
            return CoroutineResult.Failure(e)
        }
    }
}