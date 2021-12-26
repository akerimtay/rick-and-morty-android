package com.akerimtay.rickandmorty.common.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter

class PagedContentAdapter<T : ContentType>(
    typeSet: Array<T>
) : PagingDataAdapter<BaseContentItem<T>, BaseHolder<T, BaseContentItem<T>>>(BaseContentItem.DiffItem()) {
    private val delegateAdapter = ContentAdapter(typeSet)

    override fun getItemViewType(position: Int) = getItem(position)!!.type.type()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        delegateAdapter.onCreateViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: BaseHolder<T, BaseContentItem<T>>, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onBindViewHolder(
        holder: BaseHolder<T, BaseContentItem<T>>,
        position: Int,
        payloads: MutableList<Any>
    ) = (payloads.firstOrNull()).let { status ->
        if (status == null || status !is Set<*>) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            getItem(position)?.let { holder.updateHolder(it, status as Set<String>) } ?: Unit
        }
    }

    override fun onViewRecycled(holder: BaseHolder<T, BaseContentItem<T>>) =
        holder.onViewRecycled()

    override fun onViewAttachedToWindow(holder: BaseHolder<T, BaseContentItem<T>>) =
        holder.onViewAttachedToWindow()

    override fun onViewDetachedFromWindow(holder: BaseHolder<T, BaseContentItem<T>>) =
        holder.onViewDetachedFromWindow()
}