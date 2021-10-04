package com.test.korzh.giphyassignment.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.korzh.giphyassignment.data.source.local.model.Giphy
import kotlinx.coroutines.flow.Flow


@Dao
interface GiphyDao {

    @Query("SELECT * FROM GIPHY")
    fun observeFavorites(): Flow<List<Giphy>>

    @Query("SELECT * FROM GIPHY WHERE gifUrl = :url")
    fun getGiphyByUrl(url: String): Giphy?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGiphy(giphy: Giphy)

    @Query("DELETE FROM GIPHY WHERE gifUrl = :url")
    fun deleteFromFavorites(url: String): Int
}