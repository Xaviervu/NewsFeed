package ru.vegax.xavier.newsfeed.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TitleImage {

    @SerializedName("credits")
    @Expose
    var credits: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null

}