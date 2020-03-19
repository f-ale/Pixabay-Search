package com.francescoalessi.pixabaysearch

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.francescoalessi.pixabaysearch.ui.SearchFragment
import com.francescoalessi.pixabaysearch.ui.SearchViewModel
import kotlinx.android.synthetic.main.main_activity.*
import javax.inject.Inject

class MainActivity : AppCompatActivity()
{
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        (applicationContext as PixabayApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        viewModel = ViewModelProvider(this,viewModelFactory).get(SearchViewModel::class.java)
        handleIntent(intent)

        //setSupportActionBar(toolbar)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onNewIntent(intent: Intent)
    {
        handleIntent(intent)
        super.onNewIntent(intent)
    }

    private fun handleIntent(intent: Intent)
    {
        if (Intent.ACTION_SEARCH == intent.action)
        {
            val query: String = intent.getStringExtra(SearchManager.QUERY) ?: ""
            viewModel.newSearch(query)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
