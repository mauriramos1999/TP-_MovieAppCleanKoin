package com.example.tp7_movieappcleankoin.mvvm.contract

import androidx.lifecycle.LiveData
import com.example.tp7_movieappcleankoin.mvvm.viewModel.MainViewModel
import com.example.tp7_movieappcleankoin.service.model.Movie
import com.example.tp7_movieappcleankoin.util.CoroutineResult
import kotlinx.coroutines.Job

interface MainContract {

    interface ViewModel{
        fun getValue(): LiveData<MainViewModel.MainData>
        fun callService(): Job
    }

    interface Model{
        suspend fun getMovies(): CoroutineResult<List<Movie>>
    }

}