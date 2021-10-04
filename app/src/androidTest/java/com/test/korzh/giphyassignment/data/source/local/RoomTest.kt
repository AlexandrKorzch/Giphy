package com.test.korzh.giphyassignment.data.source.local

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.test.korzh.giphyassignment.data.source.local.model.Giphy
import com.test.korzh.giphyassignment.util.ServiceLocator
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RoomTest {

    private var instrumentationContext: Context? = null
    lateinit var database: GiphyDatabase
    lateinit var giphyDao : GiphyDao

    @Before
    fun setUp() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().targetContext
        instrumentationContext?.let {
            database = ServiceLocator.createDataBase(it, inMemory = true)
            giphyDao = database.giphyDao()
        }
    }

    @Test
    fun update() = runBlocking {
        val giphy = Giphy(
            title = "title",
            gifUrl = "gifUrl",
            stillUrl = "stillUrl",
            isFavorite = true,
            height = 500
        )
        giphyDao.insertGiphy(giphy)
        var giphyByUrl = giphyDao.getGiphyByUrl(giphy.gifUrl)
        Assert.assertNotNull(giphyByUrl)
        assert(giphy.gifUrl == giphyByUrl?.gifUrl)
        giphyDao.deleteFromFavorites(giphy.gifUrl)
        giphyByUrl = giphyDao.getGiphyByUrl(giphy.gifUrl)
        Assert.assertNull(giphyByUrl)
        print("done")
    }
}