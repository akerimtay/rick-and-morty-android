package com.akerimtay.rickandmorty.core.presentation.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DefaultItemDecorator(
    private val divider: Int,
    private val start: Int = 0,
    private val end: Int = 0,
    private val top: Int = 0,
    private val bottom: Int = 0
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildViewHolder(view).absoluteAdapterPosition
        setSpacingForDirection(outRect, parent.layoutManager, position, state.itemCount)
    }

    private fun setSpacingForDirection(
        outRect: Rect,
        layoutManager: RecyclerView.LayoutManager?,
        position: Int,
        itemCount: Int
    ) {
        when (layoutManager) {
            is GridLayoutManager -> {
                val columnCount = layoutManager.spanCount
                outRect.right = if (position % columnCount == columnCount - 1) 0 else divider
                outRect.bottom = divider
            }
            is LinearLayoutManager -> {
                when (layoutManager.orientation) {
                    RecyclerView.HORIZONTAL -> {
                        outRect.left = start.takeIf { position == 0 } ?: 0
                        outRect.right = divider.takeIf { position < itemCount - 1 } ?: end
                    }
                    RecyclerView.VERTICAL -> {
                        outRect.top = top.takeIf { position == 0 } ?: 0
                        outRect.bottom = divider.takeIf { position < itemCount } ?: bottom
                    }
                }
            }
        }
    }
}