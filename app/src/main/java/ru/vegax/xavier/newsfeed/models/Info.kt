package ru.vegax.xavier.newsfeed.models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Info {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("rightcol")
    @Expose
    var rightcol: String? = null
    @SerializedName("modified")
    @Expose
    var modified: Int = 0

}