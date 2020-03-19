package com.francescoalessi.pixabaysearch.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.francescoalessi.pixabaysearch.PixabayApplication
import com.francescoalessi.pixabaysearch.databinding.FragmentImageDetailBinding
import javax.inject.Inject

class ImageDetailFragment : Fragment()
{
    private val args: ImageDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: FragmentImageDetailBinding

    override fun onAttach(context: Context)
    {
        (activity?.applicationContext as PixabayApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        // Inflate the layout for this fragment
        binding = FragmentImageDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        val activity: FragmentActivity = activity as FragmentActivity
        viewModel = ViewModelProvider(activity, viewModelFactory).get(SearchViewModel::class.java)

        /*
            Retrieve pixabayImage for detail view
         */
        viewModel.getSearchResults()
            .observe(
                viewLifecycleOwner,
                Observer { result -> binding.pixabayImage = result[args.imagePosition] })
    }
}
