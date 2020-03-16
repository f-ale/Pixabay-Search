package com.francescoalessi.pixabaysearch

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import com.francescoalessi.pixabaysearch.ui.SearchFragment
import com.francescoalessi.pixabaysearch.ui.SearchViewModel

class MainActivity : AppCompatActivity()
{
    lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        //(applicationContext as PixabayApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null)
        {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SearchFragment.newInstance())
                .commitNow()
        }

        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        handleIntent(intent)
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


    override fun onCreateOptionsMenu(menu: Menu): Boolean
    {
        menuInflater.inflate(R.menu.options_menu, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        return true
    }
}
