package ru.vegax.xavier.newsfeed.recycler

import android.content.Context

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CenterZoomLayoutManager(context: Context) : LinearLayoutManager(context) {
    private val TAG = "XavvLayoutManager"
    private val mShrinkAmount = 0.20f
    private val mShrinkDistance = 0.8f

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State) {
        super.onLayoutChildren(recycler, state)
        scrollVerticallyBy(0, recycler, state)
    }

    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val orientation = orientation
        if (orientation == LinearLayoutManager.VERTICAL) {
            val scrolled = super.scrollVerticallyBy(dy, recycler, state)
            val midpoint = height / 2f
            val d0 = 0f
            val d1 = mShrinkDistance * midpoint
            val s0 = 1f
            val s1 = 1f - mShrinkAmount
            for (i in 0 until childCount) {
                val child = getChildAt(i)
                val childMidpoint = (getDecoratedBottom(child!!) + getDecoratedTop(child)) / 2f
                val d = Math.min(d1, Math.abs(midpoint - childMidpoint))
                val scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0)
                child.scaleX = scale
                child.scaleY = scale
            }
            return scrolled
        } else {
            return 0
        }
    }

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val orientation = orientation
        if (orientation == LinearLayoutManager.HORIZONTAL) {
            val scrolled = super.scrollHorizontallyBy(dx, recycler, state)

            val midpoint = width / 2f
            val d0 = 0f
            val d1 = mShrinkDistance * midpoint
            val s0 = 1f
            val s1 = 1f - mShrinkAmount
            for (i in 0 until childCount) {
                val child = getChildAt(i)
                val childMidpoint = (getDecoratedRight(child!!) + getDecoratedLeft(child)) / 2f
                val d = Math.min(d1, Math.abs(midpoint - childMidpoint))
                val scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0)
                child.scaleX = scale
                child.scaleY = scale
            }
            return scrolled
        } else {
            return 0
        }

    }
}