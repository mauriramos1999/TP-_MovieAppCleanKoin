package com.example.tp7_movieappcleankoin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp7_movieappcleankoin.R
import com.example.tp7_movieappcleankoin.databinding.ItemRecyclerviewBinding
import com.example.tp7_movieappcleankoin.data.service.model.Movie

class MoviesAdapter(private val movies: List<Movie>?) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        private val binding = ItemRecyclerviewBinding.bind(view)

        fun bind(movie: Movie){
            binding.movieTitle.text = movie.original_title
            binding.movieRelease.text = movie.release_date
            binding.moviePopularity.text = movie.popularity.toString()
            binding.movieAverageVotes.text = movie.vote_average.toString()


            Glide.with(view.context)
                .load(POSTER_PATH_URI + movie.poster_path)
                .into(binding.moviePoster)
        }

        companion object{
            private const val POSTER_PATH_URI = "https://image.tmdb.org/t/p/original"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.
            from(parent.context).
            inflate(R.layout.item_recyclerview, parent,false)
        )
    }

    override fun getItemCount(): Int {
        return movies!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies!![position])
    }
}
