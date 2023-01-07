
package com.air.movieapp.view.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.air.movieapp.MovieApplication
import com.air.movieapp.R
import com.air.movieapp.common.Constants
import com.air.movieapp.view.base.BaseActivity
import com.air.movieapp.view.movielist.fragment.MovieListFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 * Main Container where all movie fragments are added
 */
class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initActionBar()
        initViewPager()
    }

    private fun initActionBar(){
        setSupportActionBar(toolbar)
        val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.app_name, R.string.app_name)
        drawer_layout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    private fun initViewPager() {
        tabs.setupWithViewPager(viewpager)
        setNavigationDrawer()
        viewpager.offscreenPageLimit = 3
        viewpager.adapter = getViewPagerAdapter()
    }

    private fun getViewPagerAdapter(): ViewPagerAdapter {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MovieListFragment.newInstance(Constants.TOP_RATED), getString(R.string.top_rated))
        adapter.addFragment(MovieListFragment.newInstance(Constants.UPCOMING), getString(R.string.upcoming))
        adapter.addFragment(MovieListFragment.newInstance(Constants.POPULAR), getString(R.string.popular))
        return adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchViewItem: MenuItem = menu!!.findItem(R.id.search)
        val searchView: SearchView = MenuItemCompat.getActionView(searchViewItem) as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                currentFragment!!.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_sort_title -> currentFragment!!.sortBy(Constants.SortType.TITLE)
        }
        return true
    }

    private fun setNavigationDrawer() {
        val navView: NavigationView = findViewById(R.id.navigation_view)
        navView.setNavigationItemSelectedListener { menuItem ->
            var pos = 0
            when (menuItem.itemId) {
                R.id.item_top_rated -> pos = 0
                R.id.item_upcoming -> pos = 1
                R.id.item_popular -> pos = 2
            }
            viewpager.setCurrentItem(pos, true)
            drawer_layout.closeDrawers()
            true
        }
    }

    override fun setupActivityComponent() {
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {
        private val mFragmentList: ArrayList<MovieListFragment?> = ArrayList()
        private val mFragmentTitleList: ArrayList<String?> = ArrayList()

        override fun getItem(position: Int): MovieListFragment {
            return mFragmentList[position]!!
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }


        fun addFragment(fragment: MovieListFragment?, title: String?) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }

    override fun onDestroy() {
        MovieApplication[this@HomeActivity].releaseMovieListComponent()
        super.onDestroy()
    }

    private val currentFragment: MovieListFragment?
        get() {
            val fragment: Fragment = supportFragmentManager.fragments[viewpager.currentItem]
            return if (fragment is MovieListFragment) fragment else null
        }

}