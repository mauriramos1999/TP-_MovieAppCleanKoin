package com.example.tp7_movieappcleankoin.dependencies

import androidx.room.Room
import com.example.tp7_movieappcleankoin.MyApp
import com.example.tp7_movieappcleankoin.database.MovieDatabase
import com.example.tp7_movieappcleankoin.database.MovieDatabaseImpl
import com.example.tp7_movieappcleankoin.database.MovieRoomDatabase
import com.example.tp7_movieappcleankoin.mvvm.contract.MainContract
import com.example.tp7_movieappcleankoin.mvvm.model.MainModel
import com.example.tp7_movieappcleankoin.mvvm.viewModel.MainViewModel
import com.example.tp7_movieappcleankoin.service.MovieClient
import com.example.tp7_movieappcleankoin.service.MovieClientPopular
import com.example.tp7_movieappcleankoin.service.MovieService
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
    single<MovieClient> { provideService(get(), MovieClientPopular::class.java) }
    single { MovieService(get()) }
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

val modelModule = module {
    single<MainContract.Model> { MainModel(get(), get()) }
}
val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}