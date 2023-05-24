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
import com.example.tp7_movieappcleankoin.presentation.viewModel.ListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding

    val viewModel: ListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener{
            val intent = Intent(this, FirstActivity::class.java)
            startActivity(intent)
        }
        viewModel.getValue().observe(this){updateUI(it)}

    }

    private fun updateUI(x: ListViewModel.MainData){
        //binding.textview.text = x.movies.toString()
        when(x.mainStatus){
            ListViewModel.MainStatus.SHOW_INFO ->{
                binding.recyclerView.layoutManager = LinearLayoutManager(this)
                val moviesAdapter = MoviesAdapter(x.movies)
                binding.recyclerView.adapter = moviesAdapter

                val emptyStateBinding = EmptyStateBinding.inflate(layoutInflater)
                val emptyDataObserver = EmptyDataObserver(binding.recyclerView, emptyStateBinding.root)
                moviesAdapter.registerAdapterDataObserver(emptyDataObserver)
            }
            ListViewModel.MainStatus.SHOW_DIALOG ->{
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