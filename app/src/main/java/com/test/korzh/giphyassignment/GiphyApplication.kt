package com.test.korzh.giphyassignment

import android.app.Application
import com.test.korzh.giphyassignment.data.source.Repository
import com.test.korzh.giphyassignment.util.ServiceLocator

class GiphyApplication : Application() {

    val repository: Repository
        get() = ServiceLocator.provideRepository(this)
}