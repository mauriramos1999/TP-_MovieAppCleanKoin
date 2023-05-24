package com.example.tp7_movieappcleankoin.di

import androidx.room.Room
import com.example.tp7_movieappcleankoin.MyApp
import com.example.tp7_movieappcleankoin.data.MoviesRepositoryImpl
import com.example.tp7_movieappcleankoin.data.database.MovieDatabase
import com.example.tp7_movieappcleankoin.data.database.MovieDatabaseImpl
import com.example.tp7_movieappcleankoin.data.database.MovieRoomDatabase
import com.example.tp7_movieappcleankoin.domain.repository.MoviesRepository
import com.example.tp7_movieappcleankoin.domain.useCases.GetMoviesUseCase
import com.example.tp7_movieappcleankoin.presentation.viewModel.ListViewModel
import com.example.tp7_movieappcleankoin.data.service.MovieClientPopular
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val retrofitModule = module {

    fun provideHttpClient() : OkHttpClient {

        val AUTHORIZATION = "Authorization"
        val httpClientBuilder = OkHttpClient.Builder().addInterceptor { chain ->
            val defaultRequest = chain.request()
            val defaultHttpUrl = defaultRequest.url
            val httpUrl = defaultHttpUrl.newBuilder()
                .build()
            val requestBuilder = defaultRequest.newBuilder()
                .header(
                    AUTHORIZATION,
                    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyMDAxOGJlNWVhMmY0MWE2Mzk1OWU4ODkxZmIxM2YwMSIsInN1YiI6IjY0NGFmMDE5NTk2YTkxMDUyMTU3NmMxOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.jR_KWowjGSDayM82stNIWoCv8Ps1Wtj5Gel6dFCeUPY"
                )
                .url(httpUrl)
            chain.proceed(requestBuilder.build())
        }
        return httpClientBuilder.build()
    }

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val API_MOVIE_URL = "https://api.themoviedb.org"
        return Retrofit.Builder()
            .baseUrl(API_MOVIE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun <T> provideService(retrofit: Retrofit, serviceClass: Class<T>): T{
        return retrofit.create(serviceClass)
    }

    single { provideHttpClient() }
    single { provideRetrofit(get()) }
    single { provideService(get(), MovieClientPopular::class.java) }
}


val dataBaseModule = module {

    fun getDatabase(): MovieRoomDatabase {
        val dataBase: MovieRoomDatabase by lazy {
            Room.databaseBuilder(MyApp.applicationContext(), MovieRoomDatabase::class.java, "movie-DataBase")
                .build()
        }
        return dataBase
    }
    fun getMovieDatabaseImp(database: MovieRoomDatabase): MovieDatabase {
        return MovieDatabaseImpl(database.movieDao())
    }
    single { getDatabase() }
    single<MovieDatabase> { getMovieDatabaseImp(get()) }
}

val repositoryModule = module {
    single<MoviesRepository> { MoviesRepositoryImpl(get(), get()) }
}

val useCaseModule = module {
    single { GetMoviesUseCase(get()) }
}

val viewModelModule = module {
    viewModel { ListViewModel(get()) }
}