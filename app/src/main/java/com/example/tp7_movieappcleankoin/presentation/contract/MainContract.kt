package com.example.tp7_movieappcleankoin.presentation.contract

import androidx.lifecycle.LiveData
import com.example.tp7_movieappcleankoin.presentation.viewModel.ListViewModel
import kotlinx.coroutines.Job

interface MainContract {

    interface ViewModel{
        fun getValue(): LiveData<ListViewModel.MainData>
        fun callService(): Job
    }

}