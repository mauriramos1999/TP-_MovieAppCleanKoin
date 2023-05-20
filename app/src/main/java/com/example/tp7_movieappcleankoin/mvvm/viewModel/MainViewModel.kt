package com.example.tp7_movieappcleankoin.mvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp7_movieappcleankoin.mvvm.contract.MainContract
import com.example.tp7_movieappcleankoin.service.model.Movie
import com.example.tp7_movieappcleankoin.util.CoroutineResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val model: MainContract.Model ) : ViewModel(), MainContract.ViewModel{

    private val mutableLiveData: MutableLiveData<MainData> = MutableLiveData()
    override fun getValue(): LiveData<MainData> = mutableLiveData

    override fun callService() = viewModelScope.launch{
        withContext(Dispatchers.IO) {model.getMovies()}.let { result ->
            when(result){
                is CoroutineResult.Success -> {
                    mutableLiveData.value = MainData(MainStatus.SHOW_INFO, result.data)
                }
                is CoroutineResult.Failure ->{
                    mutableLiveData.value = MainData(MainStatus.SHOW_DIALOG, null)
                }
            }
        }
    }


    data class MainData (
        val mainStatus: MainStatus,
        val movies: List<Movie>?
    )


    enum class MainStatus{
        SHOW_INFO, SHOW_DIALOG
    }

}