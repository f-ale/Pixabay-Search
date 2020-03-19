package com.francescoalessi.pixabaysearch.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.francescoalessi.pixabaysearch.PixabayApplication
import com.francescoalessi.pixabaysearch.R
import com.francescoalessi.pixabaysearch.databinding.SearchFragmentBinding
import com.francescoalessi.pixabaysearch.ui.adapter.SearchAdapter
import kotlinx.android.synthetic.main.search_fragment.*
import javax.inject.Inject

class SearchFragment : Fragment()
{
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mAdapter: SearchAdapter

    private lateinit var viewModel: SearchViewModel
    private lateinit var searchView : SearchView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context)
    {
        // Enable DI on the fragment
        (activity?.applicationContext as PixabayApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        // Use data binding to inflate the layout
        val binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater)
    {
        inflater.inflate(R.menu.options_menu, menu)

        /*
            Enable search functionality in the action bar
         */
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            searchView = this
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        val activity: FragmentActivity = activity as FragmentActivity
        viewModel = ViewModelProvider(activity,viewModelFactory).get(SearchViewModel::class.java)

        /*
            Set up the recyclerview
         */
        rv_search_results.layoutManager = LinearLayoutManager(activity)
        mAdapter = SearchAdapter()
        rv_search_results.adapter = mAdapter

        /*
            Set adapter's search results once they are available
         */
        viewModel.getSearchResults()
            .observe(viewLifecycleOwner, Observer
            {
                    result -> mAdapter.setSearchResults(result)

                    // Show no results textview when there are no results
                    if(result.isNotEmpty())
                        tv_no_results.visibility = View.INVISIBLE
                    else
                        tv_no_results.visibility = View.VISIBLE
                    // Scroll back to top when a new search happens
                    mAdapter.registerAdapterDataObserver(DataObserver(rv_search_results))
            })

        /*
            Show error message if there is a connection error.
         */
        viewModel.getConnectionError()
            .observe(viewLifecycleOwner, Observer
            {
                if(it == true)
                {
                    tv_network_error.visibility = View.VISIBLE
                    rv_search_results.visibility = View.INVISIBLE
                }
                else
                {
                    tv_network_error.visibility = View.INVISIBLE
                    rv_search_results.visibility = View.VISIBLE
                }
            })
    }
}

class DataObserver(private val recyclerView: RecyclerView) : RecyclerView.AdapterDataObserver()
{
    @Override
    override fun onChanged()
    {
        recyclerView.scrollToPosition(0)
    }
}
