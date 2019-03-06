package ru.vegax.xavier.newsfeed.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HeadlineList {
    @SerializedName("headlines")
    @Expose
    var headLines: List<Headline>? = null
}