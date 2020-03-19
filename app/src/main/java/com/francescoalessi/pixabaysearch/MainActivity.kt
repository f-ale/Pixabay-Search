package com.francescoalessi.pixabaysearch

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.francescoalessi.pixabaysearch.ui.SearchViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity()
{
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        (applicationContext as PixabayApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
        handleIntent(intent)

        /*
            Use the navigation component to handle the action bar
         */
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onNewIntent(intent: Intent)
    {
        handleIntent(intent)
        super.onNewIntent(intent)
    }

    /*
        Handles search intent from ActionBar searchView
     */
    private fun handleIntent(intent: Intent)
    {
        if (Intent.ACTION_SEARCH == intent.action)
        {
            val query: String = intent.getStringExtra(SearchManager.QUERY) ?: ""
            viewModel.newSearch(query)
        }
    }

    /*
        Assign the navigation controller to handle up navigation
     */
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
