package com.blighter.tinkofflab.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blighter.tinkofflab.networkUtils.GifResponse

@Dao
interface GifDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGif(gif: GifResponse)

    @Query("SELECT * FROM gifCache WHERE id = :id")
    fun getGifById(id: Int): GifResponse
}