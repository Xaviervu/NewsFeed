package ru.vegax.xavier.newsfeed.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Rubric {

    @SerializedName("slug")
    @Expose
    var slug: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null

}