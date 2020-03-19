package com.francescoalessi.pixabaysearch.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.bumptech.glide.Glide
import com.francescoalessi.pixabaysearch.R
import com.francescoalessi.pixabaysearch.databinding.PixabayListItemBinding
import com.francescoalessi.pixabaysearch.model.PixabayImage
import kotlinx.android.synthetic.main.pixabay_list_item.view.*

class SearchAdapter() : RecyclerView.Adapter<SearchAdapter.ViewHolder>()
{
    private var mSearchResults: List<PixabayImage> = emptyList()

    fun setSearchResults(searchResults: List<PixabayImage>)
    {
        mSearchResults = searchResults
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = PixabayListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val item = mSearchResults[position]
        holder.binding.pixabayImage = item
    }

    override fun getItemCount(): Int = mSearchResults.size

    inner class ViewHolder(val binding: PixabayListItemBinding)
        : RecyclerView.ViewHolder(binding.root)
    {
        init
        {
            val viewHolder = this
            binding.setClickListener {
                MaterialDialog(it.context).show {
                    title(text = "Would you like to view more details?")
                    positiveButton(text = "View") { dialog ->
                        it.findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToImageDetailFragment(viewHolder.adapterPosition))
                        dialog.dismiss()
                    }
                    negativeButton(text = "Cancel") { dialog ->
                        dialog.dismiss()
                    }
                    lifecycleOwner(it.findFragment())
                }
            }
        }
    }
}