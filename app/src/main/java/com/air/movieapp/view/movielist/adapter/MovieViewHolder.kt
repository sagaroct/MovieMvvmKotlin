package com.air.movieapp.view.movielist.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.air.movieapp.databinding.ViewMovieBinding

/**
 * Created by sagar on 10/5/18.
 */
class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var binding: ViewMovieBinding? = DataBindingUtil.bind(view)

}