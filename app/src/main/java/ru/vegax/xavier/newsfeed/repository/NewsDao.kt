package ru.vegax.xavier.newsfeed.repository


import androidx.paging.DataSource
import androidx.room.*

@Dao
interface NewsDao {

    @Query("SELECT * from news_table")
    fun allNews(): DataSource.Factory<Int, NewsData>

    @Query("SELECT * FROM news_table WHERE id = :newsId")
    fun newsById(newsId: String): List<NewsData>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(news: List<NewsData>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(singleNews: NewsData)

    @Query("DELETE FROM news_table")
    fun deleteAll()

    @Delete
    fun delete(newsData: NewsData)
}