package com.example.tp7_movieappcleankoin.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp7_movieappcleankoin.presentation.recyclerView.adapter.MoviesAdapter
import com.example.tp7_movieappcleankoin.databinding.ActivityListBinding
import com.example.tp7_movieappcleankoin.databinding.EmptyStateBinding
import com.example.tp7_movieappcleankoin.dialogFragment.FragmentError
import com.example.tp7_movieappcleankoin.emptyData.EmptyDataObserver
import com.example.tp7_movieappcleankoin.presentation.viewModel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    //private lateinit var viewModel: MainContract.ViewModel
    val viewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener{
            val intent = Intent(this, FirstActivity::class.java)
            startActivity(intent)
        }

        /*val dataBase: MovieRoomDatabase by lazy {
            Room.databaseBuilder(this, MovieRoomDatabase::class.java, "movie-DataBase")
                .build()
        }*/

        //val movieClient: MovieClient by inject()
        //val movieService: MovieService by inject()
        //val movieDatabase: MovieDatabase by inject()
        //val mainModel: MainContract.Model by inject()


        /*viewModel = ViewModelProvider(this,
            ViewModelFactory(
                arrayOf(
                    //MainModel(movieService, movieDatabase)
                    mainModel
        )
        )).
        get(MainViewModel::class.java)*/


        viewModel.getValue().observe(this){updateUI(it)}

    }

    private fun updateUI(x: MainViewModel.MainData){
        //binding.textview.text = x.movies.toString()
        when(x.mainStatus){
            MainViewModel.MainStatus.SHOW_INFO ->{
                binding.recyclerView.layoutManager = LinearLayoutManager(this)
                val moviesAdapter = MoviesAdapter(x.movies)
                binding.recyclerView.adapter = moviesAdapter

                val hola = EmptyStateBinding.inflate(layoutInflater)
                val emptyDataObserver = EmptyDataObserver(binding.recyclerView, hola.root)
                moviesAdapter.registerAdapterDataObserver(emptyDataObserver)
            }
            MainViewModel.MainStatus.SHOW_DIALOG ->{
                val fragmentError = FragmentError.newInstance()
                fragmentError.show(supportFragmentManager, "error")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.callService()
    }
}