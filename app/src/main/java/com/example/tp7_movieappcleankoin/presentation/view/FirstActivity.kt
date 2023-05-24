package com.example.tp7_movieappcleankoin.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tp7_movieappcleankoin.databinding.ActivityFirstBinding
import com.example.tp7_movieappcleankoin.presentation.viewModel.FirstViewModel
import com.example.tp7_movieappcleankoin.presentation.viewModel.ListViewModel

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding

    private lateinit var firstViewModel: FirstViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firstViewModel = FirstViewModel()
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firstViewModel.getValue().observe(this){
            updateUI(it)}
    }

    fun updateUI(x: FirstViewModel.MainData){
        if(x.mainStatus == FirstViewModel.MainStatus.SHOW_LIST){

            binding.buttonMain.setOnClickListener {
                val intent = Intent(this, ListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        firstViewModel.showMovieListActivity()
    }

}