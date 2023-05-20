package com.example.tp7_movieappcleankoin.database

import com.example.tp7_movieappcleankoin.database.dao.MovieDAO
import com.example.tp7_movieappcleankoin.database.mapper.mapToDataBaseMovie
import com.example.tp7_movieappcleankoin.database.mapper.mapToLocalMovie
import com.example.tp7_movieappcleankoin.service.model.Movie

interface MovieDatabase {
    suspend fun insertMovies(movies: List<Movie>)
    suspend fun getAllMovies(): List<Movie>
}

class MovieDatabaseImpl(private val movieDAO: MovieDAO) : MovieDatabase {

    override suspend fun insertMovies(movies: List<Movie>) {
        movies.forEach { movie ->
            movieDAO.insertMovie(movie.mapToDataBaseMovie())
        }
    }

    override suspend fun getAllMovies(): List<Movie> {
        return movieDAO.getMovies().mapToLocalMovie()
    }
}