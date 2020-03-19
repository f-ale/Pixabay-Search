package com.francescoalessi.pixabaysearch.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.francescoalessi.pixabaysearch.PixabayApplication
import com.francescoalessi.pixabaysearch.R
import com.francescoalessi.pixabaysearch.databinding.SearchFragmentBinding
import kotlinx.android.synthetic.main.search_fragment.*
import javax.inject.Inject

class SearchFragment : Fragment()
{
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mAdapter: SearchAdapter

    lateinit var viewModel: SearchViewModel

    lateinit var searchView : SearchView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context)
    {
        (activity?.applicationContext as PixabayApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        val binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater)
    {
        inflater.inflate(R.menu.options_menu, menu)

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

        rv_search_results.layoutManager = LinearLayoutManager(activity)
        mAdapter = SearchAdapter()
        rv_search_results.adapter = mAdapter
        viewModel.getSearchResults()
            .observe(viewLifecycleOwner, Observer
            {
                    result -> mAdapter.setSearchResults(result)
            })

        viewModel.newSearchLiveData.observe(viewLifecycleOwner, Observer {
            rv_search_results.scrollToPosition(0)
        })
    }
}
