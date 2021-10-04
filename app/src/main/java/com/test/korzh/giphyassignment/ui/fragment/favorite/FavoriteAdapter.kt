package com.test.korzh.giphyassignment.ui.fragment.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.test.korzh.giphyassignment.R
import com.test.korzh.giphyassignment.data.source.local.model.Giphy
import com.test.korzh.giphyassignment.ui.fragment.GiphyClickListener

class FavoriteAdapter(private val callback: GiphyClickListener) :
    ListAdapter<Giphy, FavoriteItemViewHolder>(REPO_COMPARATOR) {

    private var dataSet: MutableList<Giphy> = mutableListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FavoriteItemViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_favorite_gif, viewGroup, false)
        return FavoriteItemViewHolder(view, callback)
    }

    override fun onBindViewHolder(viewHolder: FavoriteItemViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

    fun updateGiphyState(favorites: List<Giphy>) {
        val dataSetCount = dataSet.size
        val favoritesCount = favorites.size
        if (dataSetCount < favoritesCount) {
            addItem(dataSet, favorites, this)
        } else if (dataSetCount > favoritesCount) {
            removeItem(dataSet, favorites, this)
        }
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