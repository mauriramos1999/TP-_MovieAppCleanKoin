package com.example.tp7_movieappcleankoin.data.service

import com.example.tp7_movieappcleankoin.data.service.model.MovieList
import retrofit2.Call

interface MovieClient {

    fun getMovies(): Call<MovieList>

}