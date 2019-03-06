package ru.vegax.xavier.newsfeed.recycler


import android.content.Context
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import ru.vegax.xavier.newsfeed.repository.NewsData

class NewsAdapter(private val mContext: Context) : PagedListAdapter<NewsData, NewsViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bindTo(currentItem)
        holder.checkBoxNotify.setOnClickListener {
            currentItem?.isSelected = (it as CheckBox).isChecked

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(parent,mContext)

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<NewsData>() {
            override fun areItemsTheSame(oldItem: NewsData, newItem: NewsData): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: NewsData, newItem: NewsData): Boolean =
                oldItem == newItem
        }
    }
}
