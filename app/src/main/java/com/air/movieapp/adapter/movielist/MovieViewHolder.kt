package com.air.movieapp.adapter.movielist

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.air.movieapp.databinding.ViewMovieConstraintBinding

/**
 * Created by sagar on 10/5/18.
 */
class MovieViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
    var binding: ViewMovieConstraintBinding?
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