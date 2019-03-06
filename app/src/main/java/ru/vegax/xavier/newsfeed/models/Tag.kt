package ru.vegax.xavier.newsfeed.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Tag {

    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("slug")
    @Expose
    var slug: String? = null

}