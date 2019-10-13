package ru.vegax.xavier.newsfeed.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NewsData::class], version = 3, exportSchema = false)
abstract class NewsDb : RoomDatabase() {
    abstract fun newsDao(): NewsDao

    companion object {
        private var instance: NewsDb? = null
        internal fun get(context: Context): NewsDb {
            if (instance == null) synchronized(NewsDb::class.java) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NewsDb::class.java, "news_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance!!
        }
    }
}
