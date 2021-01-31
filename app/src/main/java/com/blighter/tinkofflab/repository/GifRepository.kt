package com.blighter.tinkofflab.repository

import android.content.Context
import androidx.room.Room
import com.blighter.tinkofflab.database.GifDatabase
import com.blighter.tinkofflab.networkUtils.Client.client
import com.blighter.tinkofflab.networkUtils.GifInterface
import com.blighter.tinkofflab.networkUtils.GifResponse
import kotlinx.coroutines.*

class GifRepository(private val context: Context) {
    val database = Room.databaseBuilder(
        context,
        GifDatabase::class.java, "database-name"
    ).build()

    suspend fun getGifFromApiAsync() = withContext(Dispatchers.IO) {
        async {
            val gif: GifResponse? = try {
                client.create(GifInterface::class.java).getGif().execute().body()
            } catch (e: Exception) {
                null
            }
            gif
        }
    }

    suspend fun insertGif(id: Int, gifResponse: GifResponse) {
        withContext(Dispatchers.IO) {
            gifResponse.id = id
            database.gifDao().addGif(gifResponse)

        }
    }

    suspend fun getGifFromDaoAsync(id: Int) = withContext(Dispatchers.IO) {
        async {
            database.gifDao().getGifById(id)
        }
    }
    suspend fun deleteDatabase(){
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                database.clearAllTables()

            }
        }
    }
}