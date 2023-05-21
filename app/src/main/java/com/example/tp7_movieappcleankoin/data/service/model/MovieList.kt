package com.example.tp7_movieappcleankoin.data.service.model

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("page")
    var page: Int,
    @SerializedName("total_pages")
    var total_pages: String,
    @SerializedName("total_results")
    var total_results: String,
    @SerializedName("results")
    var movies: List<Movie>
)