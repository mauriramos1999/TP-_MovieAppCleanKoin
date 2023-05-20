package com.example.tp7_movieappcleankoin.service

import com.example.tp7_movieappcleankoin.service.model.MovieList
import retrofit2.Call

interface MovieClient {

    fun getMovies(): Call<MovieList>

}