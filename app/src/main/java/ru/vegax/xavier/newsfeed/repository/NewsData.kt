package ru.vegax.xavier.newsfeed.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "news_table")
class NewsData(rowId: Int, id: String, title: String, content: String, imageUrl: String) {
    @field:PrimaryKey(autoGenerate = false)
    var rowId: Int = rowId

    @field:ColumnInfo(name = "id")
    val id: String = id

    @field:ColumnInfo(name = "title")
    val title: String = title

    @field:ColumnInfo(name = "content")
    val content: String = content

    @field:ColumnInfo(name = "imageUrl")
    val imageUrl: String = imageUrl

    var isSelected = false
}