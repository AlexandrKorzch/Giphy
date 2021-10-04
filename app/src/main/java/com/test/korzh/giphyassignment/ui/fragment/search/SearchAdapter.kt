package com.test.korzh.giphyassignment.ui.fragment.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.test.korzh.giphyassignment.R
import com.test.korzh.giphyassignment.data.source.local.model.Giphy
import com.test.korzh.giphyassignment.ui.fragment.GiphyClickListener

class SearchAdapter(private val callback: GiphyClickListener) :
    ListAdapter<Giphy, SearchItemViewHolder>(REPO_COMPARATOR) {

    private var dataSet: MutableList<Giphy> = mutableListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SearchItemViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_search_gif, viewGroup, false)
        return SearchItemViewHolder(view, callback)
    }

    override fun onBindViewHolder(viewHolder: SearchItemViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

    fun setDataSet(dataSet: List<Giphy>) {
        val itemCount = itemCount
        this.dataSet.addAll(dataSet)
        this.notifyItemRangeInserted(itemCount, dataSet.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateGiphyState(favorites: List<Giphy>) {
        dataSet.forEachIndexed { index, giphy ->
            var find = false
            run {
                favorites.forEach {
                    if (giphy.gifUrl == it.gifUrl)
                        find = true
                    if (giphy.isFavorite != it.isFavorite) {
                        giphy.isFavorite = it.isFavorite
                        notifyItemChanged(index)
                    }
                }
            }
            if (!find) {
                giphy.isFavorite = false
                notifyItemChanged(index)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        dataSet.clear()
        notifyDataSetChanged()
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Giphy>() {
            override fun areItemsTheSame(oldItem: Giphy, newItem: Giphy): Boolean =
                oldItem.gifUrl == newItem.gifUrl

            override fun areContentsTheSame(oldItem: Giphy, newItem: Giphy): Boolean =
                oldItem.gifUrl == newItem.gifUrl
        }
    }
}