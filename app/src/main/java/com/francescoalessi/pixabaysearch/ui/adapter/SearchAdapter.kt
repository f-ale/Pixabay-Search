package com.francescoalessi.pixabaysearch.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.francescoalessi.pixabaysearch.R
import com.francescoalessi.pixabaysearch.databinding.PixabayListItemBinding
import com.francescoalessi.pixabaysearch.model.PixabayImage
import com.francescoalessi.pixabaysearch.ui.SearchFragmentDirections

class SearchAdapter : PagedListAdapter<PixabayImage, SearchAdapter.ViewHolder>(DIFF_CALLBACK)
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view =
            PixabayListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val item = getItem(position)
        holder.binding.pixabayImage = item
    }

    inner class ViewHolder(val binding: PixabayListItemBinding) :
        RecyclerView.ViewHolder(binding.root)
    {
        init
        {
            val viewHolder = this
            /*
                Summon detail dialog on click
             */
            binding.setClickListener {
                MaterialDialog(it.context).show {
                    title(R.string.dialog_title)
                    positiveButton(R.string.dialog_accept)
                    { dialog ->
                        // Use navigation component to navigate to detail fragment
                        it.findNavController().navigate(
                            SearchFragmentDirections.actionSearchFragmentToImageDetailFragment(
                                viewHolder.adapterPosition
                            )
                        )
                        dialog.dismiss()
                    }
                    negativeButton(R.string.dialog_deny)
                    { dialog ->
                        dialog.dismiss()
                    }
                    // Assign the view's fragment as the dialog's lifecycle owner
                    lifecycleOwner(it.findFragment())
                }
            }
        }
    }
}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PixabayImage>()
{
    override fun areItemsTheSame(oldItem: PixabayImage, newItem: PixabayImage): Boolean
    {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PixabayImage, newItem: PixabayImage): Boolean
    {
        return oldItem == newItem
    }

}