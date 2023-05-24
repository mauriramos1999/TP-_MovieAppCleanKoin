package com.example.tp7_movieappcleankoin.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FirstViewModel : ViewModel() {

    private val mutableLiveData: MutableLiveData<MainData> = MutableLiveData()
    fun getValue(): LiveData<MainData> = mutableLiveData

    fun showMovieListActivity(){
        mutableLiveData.value = MainData(MainStatus.SHOW_LIST)
    }

    data class MainData (
        val mainStatus: MainStatus
    )

    enum class MainStatus{
        SHOW_LIST
    }
}