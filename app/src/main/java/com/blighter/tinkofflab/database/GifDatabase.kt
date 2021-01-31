package com.blighter.tinkofflab.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blighter.tinkofflab.networkUtils.GifResponse

@Database(entities = [GifResponse::class], version = 1, exportSchema = false)
abstract class GifDatabase : RoomDatabase() {
    abstract fun gifDao(): GifDao
}