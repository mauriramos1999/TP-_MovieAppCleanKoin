package com.example.tp7_movieappcleankoin.data.database

import com.example.tp7_movieappcleankoin.data.database.dao.MovieDAO
import com.example.tp7_movieappcleankoin.data.database.mapper.mapToDataBaseMovie
import com.example.tp7_movieappcleankoin.data.database.mapper.mapToLocalMovie
import com.example.tp7_movieappcleankoin.data.service.model.Movie

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