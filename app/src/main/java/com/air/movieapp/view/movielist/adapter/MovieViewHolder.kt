package com.air.movieapp.view.movielist.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.air.movieapp.databinding.ViewMovieBinding

/**
 * Created by sagar on 10/5/18.
 */
class MovieViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
    var binding: ViewMovieBinding?
/*    private fun makeInvisibleTitlesVisible(movieList: List<Movie?>?) {
        for (movie in movieList!!) {
            movie?.isVisible = true
        }
    }*/

    init {
        binding = DataBindingUtil.bind(view!!)
        /*view.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                makeInvisibleTitlesVisible(movieList)
                val movie: Movie = binding!!.getMovie()
                movie.isVisible = false

                Log.d("MovieListAdapter", "onClick: " + movie.getTitle())
            }
        })*/
    }
}