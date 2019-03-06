package ru.vegax.xavier.newsfeed.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Headline {

    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("info")
    @Expose
    var info: Info? = null
    @SerializedName("links")
    @Expose
    var links: Links? = null
    @SerializedName("rubric")
    @Expose
    var rubric: Rubric? = null
    @SerializedName("tags")
    @Expose
    var tags: List<Tag>? = null
    @SerializedName("title_image")
    @Expose
    var titleImage: TitleImage? = null
    @SerializedName("authors")
    @Expose
    var authors: List<Author>? = null

}