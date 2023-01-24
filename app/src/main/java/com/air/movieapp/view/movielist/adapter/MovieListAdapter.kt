
package com.air.movieapp.view.movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.air.movieapp.R
import com.air.movieapp.data.model.Movie
import com.air.movieapp.databinding.ViewMovieBinding

/**
 * Created by sagar on 20/8/16.
 */
class MovieListAdapter(private val mMovieList: ArrayList<Movie>) : RecyclerView.Adapter<MovieViewHolder>(), Filterable {

    override fun getItemCount() = mMovieList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MovieViewHolder {
        val binding: ViewMovieBinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.view_movie,
                viewGroup, false)
        return MovieViewHolder(binding.getRoot())
    }

    override fun onBindViewHolder(viewHolder: MovieViewHolder, position: Int) {
        val movie = mMovieList[position]
        viewHolder.binding?.movie = movie
    }

    fun setData(data: List<Movie>) {
        mMovieList.clear()
        mMovieList.addAll(data)
        notifyDataSetChanged()
    }

    private val filterList = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = arrayListOf<Movie>()
            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(mMovieList)
            } else {
                val filterPattern: String = constraint.toString().toLowerCase().trim()
                for (item in mMovieList) {
                    if (item.title!!.toLowerCase().contains(filterPattern)
                            || item.type!!.toLowerCase().contains(filterPattern)
                            || item.release_date!!.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            if (results.values != null && results.values is List<*>) {
                mMovieList.clear()
                mMovieList.addAll(results.values as List<Movie>)
                notifyDataSetChanged()
            }
        }
    }

    override fun getFilter(): Filter {
        return filterList
    }


}