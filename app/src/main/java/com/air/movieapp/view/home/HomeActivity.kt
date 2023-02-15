
package com.air.movieapp.view.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import com.air.movieapp.R
import com.air.movieapp.common.Constants
import com.air.movieapp.common.Constants.CATEGORIES
import com.air.movieapp.view.movielist.adapter.MovieListPagerAdapter
import com.air.movieapp.view.movielist.fragment.MovieListFragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*

/**
 * Main Container where all movie fragments are added
 */
@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initActionBar()
        initViewPager()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchViewItem: MenuItem = menu.findItem(R.id.search)
        val searchView: SearchView = MenuItemCompat.getActionView(searchViewItem) as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                currentFragment?.filter(query)
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
            R.id.menu_sort_title -> currentFragment?.sortBy(Constants.SortType.TITLE)
        }
        return true
    }

    private fun initActionBar(){
        setSupportActionBar(toolbar)
        val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.app_name, R.string.app_name)
        drawer_layout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    private fun initViewPager() {
        viewpager.adapter = MovieListPagerAdapter(this)
        setNavigationDrawer()
        TabLayoutMediator(tab_layout, viewpager) { tab, position ->
            tab.text = CATEGORIES[position].uppercase()
        }.attach()
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

    private val currentFragment: MovieListFragment?
        get() {
            val fragment: Fragment = supportFragmentManager.fragments[viewpager.currentItem]
            return if (fragment is MovieListFragment) fragment else null
        }

}