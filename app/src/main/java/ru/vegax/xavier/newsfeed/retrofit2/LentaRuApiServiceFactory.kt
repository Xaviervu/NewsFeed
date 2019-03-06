package ru.vegax.xavier.newsfeed.retrofit2

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object LentaRuApiServiceFactory {
    fun createService(): LentaRuApi = Retrofit.Builder()
        .baseUrl("https://api.lenta.ru")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
        .create(LentaRuApi::class.java)

}