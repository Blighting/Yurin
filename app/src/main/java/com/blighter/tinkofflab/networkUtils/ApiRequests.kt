package com.blighter.tinkofflab.networkUtils

import retrofit2.Call
import retrofit2.http.GET

interface GifInterface {
    @GET("random?json=true")
    fun getGif(): Call<GifResponse?>
}