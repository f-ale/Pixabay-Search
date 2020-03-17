package com.francescoalessi.pixabaysearch.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager

import com.francescoalessi.pixabaysearch.R
import com.francescoalessi.pixabaysearch.databinding.FragmentImageDetailBinding
import kotlinx.android.synthetic.main.pixabay_list_item.*
import kotlinx.android.synthetic.main.search_fragment.*

class ImageDetailFragment : Fragment()
{
    val args: ImageDetailFragmentArgs by navArgs()

    lateinit var viewModel: SearchViewModel
    lateinit var binding: FragmentImageDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        binding = FragmentImageDetailBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        val activity: FragmentActivity = activity as FragmentActivity
        viewModel = ViewModelProviders.of(activity).get(SearchViewModel::class.java)
        // TODO: Use the ViewModel
       viewModel.getSearchResults()
            .observe(viewLifecycleOwner, Observer { result -> binding.pixabayImage = result[args.imagePosition] })
    }

    companion object
    {
        fun newInstance() = ImageDetailFragment()
    }
}
