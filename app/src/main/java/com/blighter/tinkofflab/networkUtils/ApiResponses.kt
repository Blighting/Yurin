package com.blighter.tinkofflab.networkUtils

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gifCache")
data class GifResponse(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val description: String,
    val gifURL: String
)