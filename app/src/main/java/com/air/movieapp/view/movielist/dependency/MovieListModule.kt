package com.air.movieapp.view.movielist.dependency

import android.content.Context
import com.air.movieapp.view.movielist.adapter.MovieListAdapter
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