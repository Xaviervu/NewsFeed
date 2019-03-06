package ru.vegax.xavier.newsfeed.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class News {

    @SerializedName("headlines")
    @Expose
    var headlines: List<Headline>? = null

}