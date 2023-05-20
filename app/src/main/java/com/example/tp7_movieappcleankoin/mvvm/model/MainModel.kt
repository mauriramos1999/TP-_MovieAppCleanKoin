package com.example.tp7_movieappcleankoin.mvvm.model

import com.example.tp7_movieappcleankoin.database.MovieDatabase
import com.example.tp7_movieappcleankoin.mvvm.contract.MainContract
import com.example.tp7_movieappcleankoin.service.MovieService
import com.example.tp7_movieappcleankoin.service.model.Movie
import com.example.tp7_movieappcleankoin.util.CoroutineResult

class MainModel(private val movieService: MovieService,
                private val database: MovieDatabase
) : MainContract.Model{

    override suspend fun getMovies(): CoroutineResult<List<Movie>> {
        return when(val movies = movieService.getMovies()){
            is CoroutineResult.Success -> {
                database.insertMovies(movies.data.movies)
                CoroutineResult.Success(database.getAllMovies())
                //CoroutineResult.Success(movies.data.movies)
            }
            is CoroutineResult.Failure ->{
                //CoroutineResult.Success(database.getAllMovies())
                CoroutineResult.Failure(movies.exception)
            }
        }
    }
}