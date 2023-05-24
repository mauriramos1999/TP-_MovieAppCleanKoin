package com.example.tp7_movieappcleankoin.data.service

import com.example.tp7_movieappcleankoin.data.service.model.MovieList
import retrofit2.Call
import retrofit2.http.GET

interface MovieClientPopular{

    @GET("/3/movie/popular")
    fun getMovies(): Call<MovieList>
}