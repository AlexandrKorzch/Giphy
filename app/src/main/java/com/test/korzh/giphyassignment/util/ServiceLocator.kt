package com.test.korzh.giphyassignment.util

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.test.korzh.giphyassignment.data.source.Repository
import com.test.korzh.giphyassignment.data.source.RepositoryImpl
import com.test.korzh.giphyassignment.data.source.local.GiphyDatabase
import com.test.korzh.giphyassignment.data.source.local.LocalDataSource
import com.test.korzh.giphyassignment.data.source.local.LocalDataSourceImpl
import com.test.korzh.giphyassignment.data.source.remote.RemoteDataSource
import com.test.korzh.giphyassignment.data.source.remote.RemoteDataSourceImpl
import com.test.korzh.giphyassignment.data.source.remote.ktorHttpClient

object ServiceLocator {

    private var database: GiphyDatabase? = null

    @Volatile
    var repository: Repository? = null
        @VisibleForTesting set

    fun provideRepository(context: Context): Repository {
        synchronized(this) {
            return repository ?: repository ?: createRepository(context)
        }
    }

    private fun createRepository(context: Context): Repository {
        val newRepo = RepositoryImpl(createRemoteDataSource(context), createLocalDataSource(context))
        repository = newRepo
        return newRepo
    }

    private fun createRemoteDataSource(context: Context): RemoteDataSource {
        return RemoteDataSourceImpl(ktorHttpClient)
    }

    private fun createLocalDataSource(context: Context): LocalDataSource {
        val database = database ?: createDataBase(context)
        return LocalDataSourceImpl(database.giphyDao())
    }

    @VisibleForTesting
    fun createDataBase(
        context: Context,
        inMemory: Boolean = false
    ): GiphyDatabase {
        val result = if (inMemory) {
            Room.inMemoryDatabaseBuilder(context.applicationContext, GiphyDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        } else {
            Room.databaseBuilder(
                context.applicationContext,
                GiphyDatabase::class.java, "Giphy.db"
            ).build()
        }
        database = result
        return result
    }
}