package ru.vegax.xavier.newsfeed.main

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_main.*
import ru.vegax.xavier.newsfeed.R
import ru.vegax.xavier.newsfeed.recycler.CenterZoomLayoutManager
import ru.vegax.xavier.newsfeed.recycler.NewsAdapter
import ru.vegax.xavier.newsfeed.repository.LoadingStatus

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private val TAG = "XavvMainActivity"

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private val layoutManager: CenterZoomLayoutManager by lazy {
        CenterZoomLayoutManager(
            this
        )
    }
    private lateinit var newsAdapter: NewsAdapter

    private val notificationHandler by lazy {
        NotificationHandler(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        swipe_container.setOnRefreshListener(this)
        observeViewModel()
    }

    private fun initRecyclerView() {
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerVUsers.layoutManager = layoutManager

        newsAdapter = NewsAdapter(this)
        recyclerVUsers.adapter = newsAdapter
        recyclerVUsers.addItemDecoration(VerticalSpaceItemDecoration(40))
        recyclerVUsers.setHasFixedSize(true)

    }

    inner class VerticalSpaceItemDecoration internal constructor(private val verticalSpaceHeight: Int) :
        RecyclerView.ItemDecoration() { // separation between cards
        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.bottom = verticalSpaceHeight
        }

    }

    private fun observeViewModel() {
        viewModel.allNews.observe(this, Observer {
            newsAdapter.submitList(it)
            tryToScroll(intent)
        })
        viewModel.loadingStatus.observe(this, Observer {
            when (it) {
                LoadingStatus.LOADING -> {
                    swipe_container.isRefreshing = true
                }
                LoadingStatus.NOT_LOADING -> {
                    swipe_container.isRefreshing = false
                }
                LoadingStatus.ERROR -> {
                    swipe_container.isRefreshing = false
                    toast(getString(R.string.refreshError))
                }
            }
        })
        viewModel.moveTo.observe(this, Observer {
            layoutManager.scrollToPosition(it - 1)
        })
    }

    override fun onRefresh() {
        viewModel.getData()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        tryToScroll(intent)
    }

    private fun tryToScroll(intent: Intent?) {
        if (intent != null) {
            if (intent.hasExtra(notificationHandler.NEWS_ID)) { // move to the required card
                val newsId: String? = intent.extras?.getString(notificationHandler.NEWS_ID)

                if (newsId != null) {
                    viewModel.newsBy(newsId)
                }
                intent.removeExtra(notificationHandler.NEWS_ID)
            }
        }
    }

    override fun onDestroy() {
        if (isFinishing) {
            viewModel.allNews.value
                ?.forEach {
                    if (it != null && it.isSelected) {
                        notificationHandler.sendNotification(it.id.hashCode(), it.id, it.title, it.content)
                    }
                }
        }
        super.onDestroy()
    }
}
