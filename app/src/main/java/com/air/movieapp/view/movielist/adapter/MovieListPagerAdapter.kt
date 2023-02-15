package com.air.movieapp.view.movielist.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.air.movieapp.common.Constants.CATEGORIES
import com.air.movieapp.view.movielist.fragment.MovieListFragment

class MovieListPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private val categories = CATEGORIES

    override fun getItemCount() =  categories.count()

    override fun createFragment(position: Int): Fragment {
        return MovieListFragment.newInstance(categories[position])
    }

}