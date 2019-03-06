package ru.vegax.xavier.newsfeed.retrofit2


import retrofit2.Call
import retrofit2.http.GET
import ru.vegax.xavier.newsfeed.models.HeadlineList

interface LentaRuApi {
    @GET("/lists/latest")
    fun headlineList(): Call<HeadlineList>
}
