package ru.vegax.xavier.newsfeed.models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Links {

    @SerializedName("self")
    @Expose
    var self: String? = null
    @SerializedName("public")
    @Expose
    var public: String? = null

}