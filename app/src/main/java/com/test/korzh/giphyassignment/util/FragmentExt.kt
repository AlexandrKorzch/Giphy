package com.test.korzh.giphyassignment.util

import androidx.fragment.app.Fragment
import com.test.korzh.giphyassignment.GiphyApplication

fun Fragment.getViewModelFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as GiphyApplication).repository
    return ViewModelFactory(repository, this)
}