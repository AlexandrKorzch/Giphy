package com.test.korzh.giphyassignment.ui.fragment.favorite

import androidx.recyclerview.widget.ListAdapter
import com.test.korzh.giphyassignment.constant.FIRST
import com.test.korzh.giphyassignment.constant.INVALID_INDEX
import com.test.korzh.giphyassignment.data.source.local.model.Giphy

fun addItem(dataSet: MutableList<Giphy>, favorites: List<Giphy>, adapter : ListAdapter<*,*>) {
    favorites.forEachIndexed { _, giphy ->
        var find = false
        dataSet.forEach {
            if (giphy.gifUrl == it.gifUrl) {
                find = true
            }
        }
        if (!find) {
            dataSet.add(FIRST, giphy)
            adapter.notifyItemInserted(FIRST)
        }
    }
}

fun removeItem(dataSet: MutableList<Giphy>, favorites: List<Giphy>, adapter : ListAdapter<*,*>) {
    val dataSetCount = dataSet.size
    var removePosition = INVALID_INDEX
    dataSet.forEachIndexed { index, giphy ->
        var find = false
        favorites.forEach {
            if (giphy.gifUrl == it.gifUrl) {
                find = true
            }
        }
        if (!find) {
            removePosition = index
        }
    }
    if (removePosition != INVALID_INDEX) {
        dataSet.removeAt(removePosition)
        adapter.notifyItemRemoved(removePosition)
        adapter.notifyItemRangeChanged(removePosition, dataSetCount)
    }
}