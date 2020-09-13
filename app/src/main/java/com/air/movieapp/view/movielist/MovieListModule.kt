package com.air.movieapp.view.movielist

import android.content.Context
import com.air.movieapp.adapter.movielist.MovieListAdapter
import com.air.movieapp.model.Movie
import dagger.Module
import dagger.Provides

/**
 * Created by sagar on 10/8/17.
 */
@Module
class MovieListModule(context: Context?) {
    private val mContext: Context?

    @Provides //    @MovieListScope
    fun provideMovieListAdapter(): MovieListAdapter {
        return MovieListAdapter(ArrayList())
    } /* @Provides
    @MovieListScope
    public LinearLayoutManager provideLinearLayoutManager(){
        return new LinearLayoutManager(mContext);
    }*/

    init {
        mContext = context
    }
}