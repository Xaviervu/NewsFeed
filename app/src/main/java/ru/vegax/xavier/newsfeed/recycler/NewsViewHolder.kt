package ru.vegax.xavier.newsfeed.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.vegax.xavier.newsfeed.R
import ru.vegax.xavier.newsfeed.repository.NewsData


class NewsViewHolder(parent: ViewGroup, val context: Context) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.list_item_news, parent, false)
) {

    private val txtVHeader: TextView = itemView.findViewById(R.id.txtVHeader)
    private val txtVContent: TextView = itemView.findViewById(R.id.txtVContent)
    private val imgVPicture: ImageView = itemView.findViewById(R.id.imgVPicture)
    val checkBoxNotify: CheckBox = itemView.findViewById(R.id.checkBoxNotify)

    fun bindTo(currentItem: NewsData?) {
        if (currentItem != null) {
            txtVHeader.text = currentItem.title
            txtVContent.text = currentItem.content
            Glide.with(context)
                .asBitmap()
                .load(currentItem.imageUrl)
                .into(imgVPicture)
            checkBoxNotify.isChecked = currentItem.isSelected
        }

    }
}