package com.akerimtay.rickandmorty.core.presentation.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun onBind(item: T)

    open fun update(item: T, keys: Set<String>) {}

    open fun onViewRecycled() {}
}