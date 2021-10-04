package com.test.korzh.giphyassignment.ui.adapter

import androidx.annotation.StringRes
import com.test.korzh.giphyassignment.R
import com.test.korzh.giphyassignment.constant.INVALID

enum class TabName(val position: Int, @StringRes val titleRes : Int) {
    SEARCH(0, R.string.tab_1_label),
    FAVORITE(1, R.string.tab_2_label);

    companion object {
        fun getTitle(position: Int): Int =
            values().find { it.position == position }?.titleRes ?: INVALID
    }
}