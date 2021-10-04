package com.test.korzh.giphyassignment.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.test.korzh.giphyassignment.ui.fragment.favorite.FavoriteGiphyFragment
import com.test.korzh.giphyassignment.ui.fragment.search.SearchGiphyFragment
import java.lang.UnsupportedOperationException

class SectionsPagerAdapter(fm: FragmentManager, lifeCycle: Lifecycle) :
    FragmentStateAdapter(fm, lifeCycle) {

    companion object{
        const val ITEM_COUNT = 2
        const val SEARCH = 0
        const val FAVORITE = 1
    }

    override fun getItemCount() = ITEM_COUNT

    override fun createFragment(position: Int): Fragment  = when(position){
        SEARCH -> SearchGiphyFragment.newInstance()
        FAVORITE -> FavoriteGiphyFragment.newInstance()
        else -> throw UnsupportedOperationException("position - $position, should be 0..1")
    }
}