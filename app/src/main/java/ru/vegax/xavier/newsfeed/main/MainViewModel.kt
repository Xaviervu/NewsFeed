package ru.vegax.xavier.newsfeed.main


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.paging.Config
import androidx.paging.toLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.vegax.xavier.newsfeed.models.HeadlineList
import ru.vegax.xavier.newsfeed.repository.LoadingStatus
import ru.vegax.xavier.newsfeed.repository.NewsData
import ru.vegax.xavier.newsfeed.repository.NewsDb
import ru.vegax.xavier.newsfeed.retrofit2.LentaRuApiServiceFactory

internal class MainViewModel(app: Application) : AndroidViewModel(app) {
    private val TAG = "XavvViewModel"
    val dao = NewsDb.get(app).newsDao()
    private val mLoadingStatus = MutableLiveData<LoadingStatus>()
    val loadingStatus: LiveData<LoadingStatus> = mLoadingStatus

    val allNews = dao.allNews().toLiveData(
        Config(
            pageSize = 5, // data is loaded by 5 elements
            enablePlaceholders = true,
            maxSize = 100
        )
    )

    val moveTo = MutableLiveData<Int>()

    fun clearAndInsert(news: List<NewsData>) = ioThread {
        dao.deleteAll()
        dao.insert(news)
    }

    fun insert(newsData: NewsData) = ioThread {
        dao.insert(newsData)
    }

    fun remove(news: NewsData) = ioThread {
        dao.delete(news)
    }

    fun newsBy(id: String) = ioThread {

        val newsList = dao.newsById(id)
        if (newsList.isNotEmpty()) {
            val newsData = newsList[0]
            moveTo.postValue(newsData.rowId)
        }
    }

    //REST functions and values

    private val mLentaRuApiService by lazy { LentaRuApiServiceFactory.createService() }
    fun getData() {
        val userCall = mLentaRuApiService.headlineList()
        userCall.enqueue(object : Callback<HeadlineList> {
            override fun onResponse(call: Call<HeadlineList>, response: Response<HeadlineList>) {

                if (!response.isSuccessful) {
                    Log.e(TAG, "non successful response")
                    mLoadingStatus.postValue(LoadingStatus.ERROR)
                    return
                }
                mLoadingStatus.postValue(LoadingStatus.NOT_LOADING)
                val newsData = response.body()
                if (newsData != null) {
                    val responseData = gatherData(newsData)
                    if (responseData != null) {
                        clearAndInsert(responseData)
                    }
                }
            }

            override fun onFailure(call: Call<HeadlineList>, t: Throwable) {
                mLoadingStatus.postValue(LoadingStatus.LOADING)
                Log.e(TAG, "RESP call failure:" + t.message)
                mLoadingStatus.postValue(LoadingStatus.ERROR)
            }
        })
    }

    private fun gatherData(newsData: HeadlineList): List<NewsData>? {
        return newsData.headLines?.mapIndexed { i, it ->
            NewsData(
                i + 1, it.info?.id ?: ""
                , it.info?.title ?: ""
                , it.info?.rightcol ?: ""
                , it.titleImage?.url ?: ""
            )
        }
    }
}
