package com.test.korzh.giphyassignment.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.korzh.giphyassignment.constant.EMPTY


@Entity(tableName = "giphy")
data class Giphy @JvmOverloads constructor(
    @PrimaryKey @ColumnInfo(name = "gifUrl") var gifUrl: String = "",
    @ColumnInfo(name = "stillUrl") var stillUrl: String = "",
    @ColumnInfo(name = "height") var height: Int = Int.MIN_VALUE,
    @ColumnInfo(name = "title") var title: String = EMPTY,
    @ColumnInfo(name = "isFavorite") var isFavorite: Boolean = false
){
    @ColumnInfo(name = "localPath") var localPath: String? = null

    override fun equals(other: Any?): Boolean {
        return this.gifUrl == (other as? Giphy)?.gifUrl
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}