package com.test.korzh.giphyassignment.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.korzh.giphyassignment.data.source.local.model.Giphy

@Database(entities = [Giphy::class], version = 1, exportSchema = false)
abstract class GiphyDatabase : RoomDatabase() {
    abstract fun giphyDao(): GiphyDao
}