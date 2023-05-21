package com.example.tp7_movieappcleankoin.presentation.contract

import androidx.lifecycle.LiveData
import com.example.tp7_movieappcleankoin.presentation.viewModel.MainViewModel
import kotlinx.coroutines.Job

interface MainContract {

    interface ViewModel{
        fun getValue(): LiveData<MainViewModel.MainData>
        fun callService(): Job
    }

}